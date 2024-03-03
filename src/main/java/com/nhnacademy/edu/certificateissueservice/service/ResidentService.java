package com.nhnacademy.edu.certificateissueservice.service;

import com.nhnacademy.edu.certificateissueservice.dto.request.ResidentModifyRequestDto;
import com.nhnacademy.edu.certificateissueservice.dto.request.ResidentRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.Resident;

public interface ResidentService {
    Resident registerResident(ResidentRegisterRequestDto residentRegisterRequestDto);
    Resident modifyResident(Integer serialNumber, ResidentModifyRequestDto residentModifyRequestDto);
}
