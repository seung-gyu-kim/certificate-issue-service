package com.nhnacademy.edu.certificateissueservice.service.impl;

import com.nhnacademy.edu.certificateissueservice.dto.BirthDeathReportModifyRequestDto;
import com.nhnacademy.edu.certificateissueservice.dto.BirthDeathReportRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.BirthDeathReportResident;
import com.nhnacademy.edu.certificateissueservice.entity.Resident;
import com.nhnacademy.edu.certificateissueservice.exception.BirthReportResidentNotFoundException;
import com.nhnacademy.edu.certificateissueservice.exception.ResidentNotFoundException;
import com.nhnacademy.edu.certificateissueservice.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.edu.certificateissueservice.repository.ResidentRepository;
import com.nhnacademy.edu.certificateissueservice.service.BirthDeathReportResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional
public class BirthDeathReportResidentServiceImpl implements BirthDeathReportResidentService {
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final ResidentRepository residentRepository;

    @Override
    public BirthDeathReportResident registerBirthReport(Integer reportResidentSerialNumber, BirthDeathReportRegisterRequestDto request) {
        Resident resident = residentRepository.findById(request.getTargetSerialNumber())
                .orElseThrow(() -> new ResidentNotFoundException(request.getTargetSerialNumber()));

        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        BirthDeathReportResident.PK pk = new BirthDeathReportResident.PK(
                request.getTargetSerialNumber(),
                "출생",
                reportResidentSerialNumber);
        birthDeathReportResident.setPk(pk);
        birthDeathReportResident.setBirthDeathReportDate(request.getBirthDeathReportDate());
        birthDeathReportResident.setBirthReportQualificationsCode(request.getReportQualificationsCode());
        birthDeathReportResident.setEmailAddress(request.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(request.getPhoneNumber());
        birthDeathReportResident.setResident(resident);
        return birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    @Override
    public BirthDeathReportResident modifyBirthReport(Integer reportResidentSerialNumber, Integer targetResidentSerialNumber, BirthDeathReportModifyRequestDto request) {
        BirthDeathReportResident.PK pk = new BirthDeathReportResident.PK(targetResidentSerialNumber, "출생", reportResidentSerialNumber);
        BirthDeathReportResident birthReport = birthDeathReportResidentRepository.findById(pk).orElseThrow(() -> new BirthReportResidentNotFoundException(pk));

        if(!Objects.isNull(request.getBirthDeathReportDate())) birthReport.setBirthDeathReportDate(request.getBirthDeathReportDate());
        if(!Objects.isNull(request.getReportQualificationsCode())) birthReport.setBirthReportQualificationsCode(request.getReportQualificationsCode());
        if(!Objects.isNull(request.getEmailAddress())) birthReport.setEmailAddress(request.getEmailAddress());
        if(!Objects.isNull(request.getPhoneNumber())) birthReport.setPhoneNumber(request.getPhoneNumber());
        return birthReport;
    }

    @Override
    public void deleteBirthReport(Integer reportResidentSerialNumber, Integer targetResidentSerialNumber) {
        birthDeathReportResidentRepository.deleteById(new BirthDeathReportResident.PK(
                targetResidentSerialNumber, "출생", reportResidentSerialNumber));
    }
}
