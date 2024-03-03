package com.nhnacademy.edu.certificateissueservice.service;

import com.nhnacademy.edu.certificateissueservice.dto.request.BirthReportRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.BirthDeathReportResident;

public interface BirthReportResidentService {
    BirthDeathReportResident registerBirthReportResident(Integer reportSerialNumber, BirthReportRequestDto requestDto);
    BirthDeathReportResident modifyBirthReportResident(Integer reportSerialNumber, Integer targetSerialNumber, BirthReportRequestDto requestDto);
    void removeBirthReportResident(Integer reportResidentSerialNumber, Integer targetResidentSerialNumber);
}
