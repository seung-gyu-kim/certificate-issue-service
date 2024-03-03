package com.nhnacademy.edu.certificateissueservice.service;

import com.nhnacademy.edu.certificateissueservice.dto.CertificateIssueDto;
import com.nhnacademy.edu.certificateissueservice.dto.response.FamilyRelationshipCertificateResponseDto;
import com.nhnacademy.edu.certificateissueservice.dto.response.ResidentRegistrationCertificateResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CertificateIssueService {
    Page<CertificateIssueDto> getCertificateIssueList(Integer serialNumber, Pageable pageable);

    ResidentRegistrationCertificateResponseDto getResidentRegistrationCertificate(Integer serialNumber);

    FamilyRelationshipCertificateResponseDto getFamilyRelationshipCertificate(Integer serialNumber);
}
