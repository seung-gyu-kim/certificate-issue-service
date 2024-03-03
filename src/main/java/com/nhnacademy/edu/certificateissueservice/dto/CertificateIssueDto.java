package com.nhnacademy.edu.certificateissueservice.dto;

import java.time.LocalDate;

/**
 * Projection for {@link com.nhnacademy.edu.certificateissueservice.entity.CertificateIssue}
 */
public interface CertificateIssueDto {
    Long getCertificateConfirmationNumber();

    String getCertificateTypeCode();

    LocalDate getCertificateIssueDate();
}