package com.nhnacademy.edu.certificateissueservice.service;

import com.nhnacademy.edu.certificateissueservice.dto.ResidentModifyRequestDto;
import com.nhnacademy.edu.certificateissueservice.dto.ResidentRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.Resident;

public interface ResidentService {
    Resident registerResident(ResidentRegisterRequestDto residentRegisterRequestDto);
    Resident modifyResident(Integer serialNumber, ResidentModifyRequestDto residentModifyRequestDto);
}
