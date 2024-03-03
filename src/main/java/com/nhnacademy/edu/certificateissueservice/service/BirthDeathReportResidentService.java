package com.nhnacademy.edu.certificateissueservice.service;

import com.nhnacademy.edu.certificateissueservice.dto.BirthReportRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.BirthDeathReportResident;

public interface BirthDeathReportResidentService {
    BirthDeathReportResident registerBirthReportResident(Integer reportSerialNumber, BirthReportRequestDto requestDto);
    BirthDeathReportResident modifyBirthDeathReportResident(Integer reportSerialNumber, Integer targetSerialNumber, BirthReportRequestDto requestDto);
    void deleteBirthDeathReportResident(Integer reportResidentSerialNumber, Integer targetResidentSerialNumber);
}
