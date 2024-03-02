package com.nhnacademy.edu.certificateissueservice.service.impl;

import com.nhnacademy.edu.certificateissueservice.dto.HouseholdMovementAddressModifyRequestDto;
import com.nhnacademy.edu.certificateissueservice.dto.HouseholdMovementAddressRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.Household;
import com.nhnacademy.edu.certificateissueservice.entity.HouseholdMovementAddress;
import com.nhnacademy.edu.certificateissueservice.exception.HouseholdNotFoundException;
import com.nhnacademy.edu.certificateissueservice.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.edu.certificateissueservice.repository.HouseholdRepository;
import com.nhnacademy.edu.certificateissueservice.service.HouseholdMovementAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional
public class HouseholdMovementAddressImpl implements HouseholdMovementAddressService {
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;
    private final HouseholdRepository householdRepository;

    @Override
    public HouseholdMovementAddress registerHouseholdMovementAddress(HouseholdMovementAddressRegisterRequestDto requestDto) {
        Household household = householdRepository.findById(requestDto.getHouseholdSerialNumber())
                .orElseThrow(() -> new HouseholdNotFoundException(requestDto.getHouseholdSerialNumber()));

        HouseholdMovementAddress.PK pk = new HouseholdMovementAddress.PK(requestDto.getHouseholdSerialNumber(), requestDto.getHouseMovementReportDate());

        HouseholdMovementAddress householdMovementAddress = new HouseholdMovementAddress();
        householdMovementAddress.setPk(pk);
        householdMovementAddress.setHouseMovementAddress(requestDto.getHouseMovementAddress());
        householdMovementAddress.setLastAddressYn(requestDto.getLastAddressYn());
        householdMovementAddress.setHousehold(household);

        return householdMovementAddressRepository.save(householdMovementAddress);
    }

    @Override
    public HouseholdMovementAddress modifyHouseholdMovementAddress(HouseholdMovementAddressModifyRequestDto requestDto) {
        HouseholdMovementAddress.PK pk = new HouseholdMovementAddress.PK(requestDto.getHouseholdSerialNumber(), requestDto.getHouseMovementReportDate());

        HouseholdMovementAddress householdMovementAddress = new HouseholdMovementAddress();
        householdMovementAddress.setPk(pk);
        if(!Objects.isNull(requestDto.getHouseMovementAddress()))
            householdMovementAddress.setHouseMovementAddress(requestDto.getHouseMovementAddress());
        if(!Objects.isNull(requestDto.getLastAddressYn()))
            householdMovementAddress.setLastAddressYn(requestDto.getLastAddressYn());

        return householdMovementAddressRepository.save(householdMovementAddress);
    }

    @Override
    public void removeHouseholdMovementAddress(Integer householdSerialNumber, LocalDate reportDate) {
        householdMovementAddressRepository.deleteById(new HouseholdMovementAddress.PK(householdSerialNumber, reportDate));
    }
}
