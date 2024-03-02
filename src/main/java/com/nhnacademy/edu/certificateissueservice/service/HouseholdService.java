package com.nhnacademy.edu.certificateissueservice.service;

import com.nhnacademy.edu.certificateissueservice.dto.HouseholdRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.Household;

public interface HouseholdService {
    Household registerHousehold(HouseholdRegisterRequestDto householdRegisterRequestDto);
    void removeHousehold(Integer householdSerialNumber);
}
