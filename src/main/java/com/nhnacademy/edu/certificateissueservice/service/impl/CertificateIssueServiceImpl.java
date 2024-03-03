package com.nhnacademy.edu.certificateissueservice.service.impl;

import com.nhnacademy.edu.certificateissueservice.dto.CertificateIssueDto;
import com.nhnacademy.edu.certificateissueservice.dto.HouseholdCompositionResidentDto;
import com.nhnacademy.edu.certificateissueservice.dto.HouseholdMovementAddressDto;
import com.nhnacademy.edu.certificateissueservice.dto.response.ResidentRegistrationCertificateResponseDto;
import com.nhnacademy.edu.certificateissueservice.entity.CertificateIssue;
import com.nhnacademy.edu.certificateissueservice.entity.Resident;
import com.nhnacademy.edu.certificateissueservice.exception.ResidentNotFoundException;
import com.nhnacademy.edu.certificateissueservice.repository.*;
import com.nhnacademy.edu.certificateissueservice.service.CertificateIssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CertificateIssueServiceImpl implements CertificateIssueService {
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final CertificateIssueRepository certificateIssueRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    @Override
    public Page<CertificateIssueDto> getCertificateIssueList(Integer serialNumber, Pageable pageable) {
        return certificateIssueRepository.findByResident_ResidentSerialNumber(serialNumber, pageable);
    }

    @Override
    @Transactional
    public ResidentRegistrationCertificateResponseDto getResidentRegistrationCertificate(Integer serialNumber) {
        // 증명서 발급
        CertificateIssue certificateIssue = new CertificateIssue();
        Long certificateConfirmationNumber = ThreadLocalRandom.current().nextLong(1000000000000000L, 9999999999999999L);
        Resident resident = residentRepository.findById(serialNumber).orElseThrow(() -> new ResidentNotFoundException(serialNumber));
        LocalDate now = LocalDate.now();

        certificateIssue.setCertificateConfirmationNumber(certificateConfirmationNumber);
        certificateIssue.setResident(resident);
        certificateIssue.setCertificateTypeCode("주민등록등본");
        certificateIssue.setCertificateIssueDate(now);
        certificateIssueRepository.save(certificateIssue);

        // 전입 주소 목록
        List<HouseholdMovementAddressDto> householdMovementAddressHistory = householdMovementAddressRepository.findHouseholdMovementAddressHistory(serialNumber);

        // 세대 구성원 목록
        List<HouseholdCompositionResidentDto> householdCompositionResidents = householdCompositionResidentRepository.getHouseholdCompositionResidents(serialNumber);



        // 결합
        ResidentRegistrationCertificateResponseDto responseDto = new ResidentRegistrationCertificateResponseDto();
        responseDto.setCertificateIssueDate(now);
        responseDto.setCertificateConfirmationNumber(certificateConfirmationNumber);
        householdCompositionResidents.stream().filter(o -> o.getHouseholdRelationshipCode().equals("본인")).findFirst().ifPresent(o -> {
            responseDto.setName(o.getName());
            responseDto.setHouseholdCompositionChangeReasonCode(o.getHouseholdCompositionChangeReasonCode());
            responseDto.setReportDate(o.getReportDate());
        });
        responseDto.setHouseholdMovementAddressList(householdMovementAddressHistory);
        responseDto.setHouseholdCompositionResidentList(householdCompositionResidents);
        return responseDto;
    }
}
