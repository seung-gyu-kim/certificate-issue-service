package com.nhnacademy.edu.certificateissueservice.service.impl;

import com.nhnacademy.edu.certificateissueservice.dto.HouseholdRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.Household;
import com.nhnacademy.edu.certificateissueservice.entity.Resident;
import com.nhnacademy.edu.certificateissueservice.exception.ResidentNotFoundException;
import com.nhnacademy.edu.certificateissueservice.repository.HouseholdRepository;
import com.nhnacademy.edu.certificateissueservice.repository.ResidentRepository;
import com.nhnacademy.edu.certificateissueservice.service.HouseholdService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

@DependsOn("dataSourceInitializer")
@Service
@Transactional
public class HouseholdServiceImpl implements HouseholdService {
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;
    private final AtomicInteger sequence = new AtomicInteger();

    public HouseholdServiceImpl(HouseholdRepository householdRepository, ResidentRepository residentRepository) {
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
    }

    @PostConstruct
    public void init() {
        householdRepository.findAll().forEach(household ->
                sequence.set(Math.max(sequence.get(), household.getHouseholdSerialNumber())));
    }


    @Override
    public Household registerHousehold(HouseholdRegisterRequestDto requestDto) {
        Resident householdResident = residentRepository
                .findById(requestDto.getHouseholdResidentSerialNumber())
                .orElseThrow(() -> new ResidentNotFoundException(requestDto.getHouseholdResidentSerialNumber()));

        Household household = new Household();
        household.setHouseholdSerialNumber(sequence.incrementAndGet());
        household.setHouseholdCompositionDate(requestDto.getHouseholdCompositionDate());
        household.setHouseholdCompositionReasonCode(requestDto.getHouseholdCompositionReasonCode());
        household.setCurrentHouseMovementAddress(requestDto.getCurrentHouseMovementAddress());
        household.setHouseholdResident(householdResident);

        return householdRepository.save(household);
    }

    @Override
    public void removeHousehold(Integer householdSerialNumber) {
        householdRepository.deleteById(householdSerialNumber);
    }
}
