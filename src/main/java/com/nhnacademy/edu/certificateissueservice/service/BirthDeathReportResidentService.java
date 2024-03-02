package com.nhnacademy.edu.certificateissueservice.service;

import com.nhnacademy.edu.certificateissueservice.dto.BirthDeathReportModifyRequestDto;
import com.nhnacademy.edu.certificateissueservice.dto.BirthDeathReportRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.BirthDeathReportResident;

public interface BirthDeathReportResidentService {
    BirthDeathReportResident registerBirthReport(Integer reportResidentSerialNumber, BirthDeathReportRegisterRequestDto birthDeathReportRegisterRequestDto);
    BirthDeathReportResident modifyBirthReport(Integer reportResidentSerialNumber,
                                               Integer targetResidentSerialNumber,
                                               BirthDeathReportModifyRequestDto birthDeathReportRegisterRequestDto);
    void deleteBirthReport(Integer reportResidentSerialNumber, Integer targetResidentSerialNumber);
}
