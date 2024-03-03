package com.nhnacademy.edu.certificateissueservice.service;

import com.nhnacademy.edu.certificateissueservice.dto.request.HouseholdMovementAddressModifyRequestDto;
import com.nhnacademy.edu.certificateissueservice.dto.request.HouseholdMovementAddressRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.HouseholdMovementAddress;

import java.time.LocalDate;

public interface HouseholdMovementAddressService {
    HouseholdMovementAddress registerHouseholdMovementAddress(HouseholdMovementAddressRegisterRequestDto requestDto);
    HouseholdMovementAddress modifyHouseholdMovementAddress(HouseholdMovementAddressModifyRequestDto requestDto);
    void removeHouseholdMovementAddress(Integer householdSerialNumber, LocalDate reportDate);
}
