package com.nhnacademy.edu.certificateissueservice.service.impl;

import com.nhnacademy.edu.certificateissueservice.dto.DeathReportRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.BirthDeathReportResident;
import com.nhnacademy.edu.certificateissueservice.entity.Resident;
import com.nhnacademy.edu.certificateissueservice.exception.ResidentNotFoundException;
import com.nhnacademy.edu.certificateissueservice.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.edu.certificateissueservice.repository.ResidentRepository;
import com.nhnacademy.edu.certificateissueservice.service.DeathReportResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeathReportResidentServiceImpl implements DeathReportResidentService {
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final ResidentRepository residentRepository;

    @Override
    @Transactional
    public BirthDeathReportResident registerDeathReportResident(Integer reportSerialNumber, DeathReportRequestDto requestDto) {
        // 주민 정보에 사망 관련 기록 등록
        Resident targetResident = residentRepository
                .findByNameAndResidentRegistrationNumber(requestDto.getTargetResident().getName(), requestDto.getTargetResident().getResidentRegistrationNumber())
                .orElseThrow(() -> new ResidentNotFoundException());
        targetResident.setDeathDate(requestDto.getTargetResident().getDeathDate());
        targetResident.setDeathPlaceCode(requestDto.getTargetResident().getDeathPlaceCode());
        targetResident.setDeathPlaceAddress(requestDto.getTargetResident().getDeathPlaceAddress());
        residentRepository.save(targetResident);

        // 사망 신고 주민 등록
        Resident reportResident = residentRepository.findById(reportSerialNumber).orElseThrow(() -> new ResidentNotFoundException());

        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        BirthDeathReportResident.PK pk = new BirthDeathReportResident.PK(
                targetResident.getResidentSerialNumber(), "사망", reportResident.getResidentSerialNumber()
        );
        birthDeathReportResident.setPk(pk);
        birthDeathReportResident.setBirthDeathReportDate(requestDto.getDeathReportDate());
        birthDeathReportResident.setDeathReportQualificationsCode(requestDto.getReportResident().getDeathBirthReportQualificationsCode());
        birthDeathReportResident.setEmailAddress(requestDto.getReportResident().getEmailAddress());
        birthDeathReportResident.setPhoneNumber(requestDto.getReportResident().getPhoneNumber());
        birthDeathReportResident.setResident(targetResident);

        return birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    @Override
    @Transactional
    public BirthDeathReportResident modifyDeathReportResident(Integer reportSerialNumber, Integer targetSerialNumber, DeathReportRequestDto requestDto) {
        // 주민 정보에 사망 관련 기록 등록
        Resident targetResident = residentRepository
                .findById(targetSerialNumber)
                .orElseThrow(() -> new ResidentNotFoundException());
        targetResident.setDeathDate(requestDto.getTargetResident().getDeathDate());
        targetResident.setDeathPlaceCode(requestDto.getTargetResident().getDeathPlaceCode());
        targetResident.setDeathPlaceAddress(requestDto.getTargetResident().getDeathPlaceAddress());
        residentRepository.save(targetResident);

        // 사망 신고 주민 삭제 후 재등록
        birthDeathReportResidentRepository.deleteById(new BirthDeathReportResident.PK(targetSerialNumber, "사망", reportSerialNumber));

        Resident reportResident = residentRepository.findById(reportSerialNumber).orElseThrow(() -> new ResidentNotFoundException());

        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        BirthDeathReportResident.PK pk = new BirthDeathReportResident.PK(
                targetSerialNumber, "사망", reportSerialNumber
        );
        birthDeathReportResident.setPk(pk);
        birthDeathReportResident.setBirthDeathReportDate(requestDto.getDeathReportDate());
        birthDeathReportResident.setDeathReportQualificationsCode(requestDto.getReportResident().getDeathBirthReportQualificationsCode());
        birthDeathReportResident.setEmailAddress(requestDto.getReportResident().getEmailAddress());
        birthDeathReportResident.setPhoneNumber(requestDto.getReportResident().getPhoneNumber());
        birthDeathReportResident.setResident(targetResident);

        return birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    @Override
    @Transactional
    public void removeDeathReportResident(Integer reportSerialNumber, Integer targetSerialNumber) {
        Resident targetResident = residentRepository
                .findById(targetSerialNumber)
                .orElseThrow(() -> new ResidentNotFoundException());
        targetResident.setDeathDate(null);
        targetResident.setDeathPlaceCode(null);
        targetResident.setDeathPlaceAddress(null);
        residentRepository.save(targetResident);

        birthDeathReportResidentRepository.deleteById(new BirthDeathReportResident.PK(targetSerialNumber, "사망", reportSerialNumber));
    }
}
