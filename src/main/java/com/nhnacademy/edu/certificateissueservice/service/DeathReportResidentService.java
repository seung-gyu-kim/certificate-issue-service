package com.nhnacademy.edu.certificateissueservice.service;

import com.nhnacademy.edu.certificateissueservice.dto.DeathReportRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.BirthDeathReportResident;

public interface DeathReportResidentService {
    BirthDeathReportResident registerDeathReportResident(Integer reportSerialNumber, DeathReportRequestDto requestDto);
    BirthDeathReportResident modifyDeathReportResident(Integer reportSerialNumber, Integer targetSerialNumber, DeathReportRequestDto requestDto);
    void removeDeathReportResident(Integer reportSerialNumber, Integer targetSerialNumber);
}
