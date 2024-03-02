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
    public BirthDeathReportResident registerBirthDeathReportResident(BirthDeathReportRegisterRequestDto request) {
        if(Objects.isNull(request.getIsBirthReport())) throw new IllegalArgumentException("isBirthReport is null");

        Resident resident = residentRepository.findById(request.getTargetSerialNumber())
                .orElseThrow(() -> new ResidentNotFoundException(request.getTargetSerialNumber()));

        BirthDeathReportResident.PK pk = new BirthDeathReportResident.PK(
                request.getTargetSerialNumber(),
                Boolean.TRUE.equals(request.getIsBirthReport()) ? "출생" : "사망",
                request.getReportResidentSerialNumber());

        BirthDeathReportResident birthDeathReportResident = BirthDeathReportResident.of(request, pk, resident);

        return birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    @Override
    public BirthDeathReportResident modifyBirthDeathReportResident(BirthDeathReportModifyRequestDto request) {
        if(Objects.isNull(request.getIsBirthReport())) throw new IllegalArgumentException("isBirthReport is null");

        BirthDeathReportResident.PK pk = new BirthDeathReportResident.PK(
                request.getTargetSerialNumber(),
                Boolean.TRUE.equals(request.getIsBirthReport()) ? "출생" : "사망",
                request.getReportResidentSerialNumber());

        BirthDeathReportResident reportResident = birthDeathReportResidentRepository.findById(pk).orElseThrow(() -> new BirthReportResidentNotFoundException(pk));

        if(!Objects.isNull(request.getBirthDeathReportDate())) reportResident.setBirthDeathReportDate(request.getBirthDeathReportDate());
        if(!Objects.isNull(request.getReportQualificationsCode())) {
            if(Boolean.TRUE.equals(request.getIsBirthReport())) {
                reportResident.setBirthReportQualificationsCode(request.getReportQualificationsCode());
            } else {
                reportResident.setDeathReportQualificationsCode(request.getReportQualificationsCode());
            }
        }
        if(!Objects.isNull(request.getEmailAddress())) reportResident.setEmailAddress(request.getEmailAddress());
        if(!Objects.isNull(request.getPhoneNumber())) reportResident.setPhoneNumber(request.getPhoneNumber());

        return reportResident;
    }

    @Override
    public void deleteBirthDeathReportResident(Integer reportResidentSerialNumber, Integer targetResidentSerialNumber, boolean isBirthReport) {
        birthDeathReportResidentRepository.deleteById(new BirthDeathReportResident.PK(
                targetResidentSerialNumber,
                isBirthReport ? "출생" : "사망",
                reportResidentSerialNumber));
    }
}
