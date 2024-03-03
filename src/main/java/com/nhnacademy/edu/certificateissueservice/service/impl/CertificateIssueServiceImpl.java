package com.nhnacademy.edu.certificateissueservice.service.impl;

import com.nhnacademy.edu.certificateissueservice.dto.CertificateIssueDto;
import com.nhnacademy.edu.certificateissueservice.repository.*;
import com.nhnacademy.edu.certificateissueservice.service.CertificateIssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
