package com.nhnacademy.edu.certificateissueservice.service.impl;

import com.nhnacademy.edu.certificateissueservice.dto.ResidentModifyRequestDto;
import com.nhnacademy.edu.certificateissueservice.dto.ResidentRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.Resident;
import com.nhnacademy.edu.certificateissueservice.exception.ResidentNotFoundException;
import com.nhnacademy.edu.certificateissueservice.repository.ResidentRepository;
import com.nhnacademy.edu.certificateissueservice.service.ResidentService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@DependsOn("dataSourceInitializer")
@Transactional
@Service
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;
    private final AtomicInteger sequence = new AtomicInteger();

    public ResidentServiceImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @PostConstruct
    public void init() {
        residentRepository.findAll().forEach(resident ->
                sequence.set(Math.max(sequence.get(), resident.getResidentSerialNumber())));
    }

    @Override
    public Resident registerResident(ResidentRegisterRequestDto residentRegisterRequestDto) {
        Resident resident = new Resident();
        resident.setResidentSerialNumber(sequence.incrementAndGet());
        resident.setName(residentRegisterRequestDto.getName());
        resident.setResidentRegistrationNumber(residentRegisterRequestDto.getResidentRegistrationNumber());
        resident.setGenderCode(residentRegisterRequestDto.getGenderCode());
        resident.setBirthDate(residentRegisterRequestDto.getBirthDate());
        resident.setBirthPlaceCode(residentRegisterRequestDto.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(residentRegisterRequestDto.getRegistrationBaseAddress());
        resident.setDeathDate(residentRegisterRequestDto.getDeathDate());
        resident.setDeathPlaceCode(residentRegisterRequestDto.getDeathPlaceCode());
        resident.setDeathPlaceAddress(residentRegisterRequestDto.getDeathPlaceAddress());
        return residentRepository.save(resident);
    }

    @Override
    public Resident modifyResident(Integer serialNumber, ResidentModifyRequestDto residentModifyRequestDto) {
        Resident resident = residentRepository.findById(serialNumber).orElseThrow(() -> new ResidentNotFoundException(serialNumber));
        if(!Objects.isNull(residentModifyRequestDto.getName())) resident.setName(residentModifyRequestDto.getName());
        if(!Objects.isNull(residentModifyRequestDto.getResidentRegistrationNumber())) resident.setResidentRegistrationNumber(residentModifyRequestDto.getResidentRegistrationNumber());
        if(!Objects.isNull(residentModifyRequestDto.getGenderCode())) resident.setGenderCode(residentModifyRequestDto.getGenderCode());
        if(!Objects.isNull(residentModifyRequestDto.getBirthDate())) resident.setBirthDate(residentModifyRequestDto.getBirthDate());
        if(!Objects.isNull(residentModifyRequestDto.getBirthPlaceCode())) resident.setBirthPlaceCode(residentModifyRequestDto.getBirthPlaceCode());
        if(!Objects.isNull(residentModifyRequestDto.getRegistrationBaseAddress())) resident.setRegistrationBaseAddress(residentModifyRequestDto.getRegistrationBaseAddress());
        if(!Objects.isNull(residentModifyRequestDto.getDeathDate())) resident.setDeathDate(residentModifyRequestDto.getDeathDate());
        if(!Objects.isNull(residentModifyRequestDto.getDeathPlaceCode())) resident.setDeathPlaceCode(residentModifyRequestDto.getDeathPlaceCode());
        if(!Objects.isNull(residentModifyRequestDto.getDeathPlaceAddress())) resident.setDeathPlaceAddress(residentModifyRequestDto.getDeathPlaceAddress());
        return residentRepository.save(resident);
    }
}
