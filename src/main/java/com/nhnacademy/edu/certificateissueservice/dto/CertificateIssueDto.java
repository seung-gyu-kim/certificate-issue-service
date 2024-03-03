package com.nhnacademy.edu.certificateissueservice.dto;

import java.time.LocalDate;

public interface CertificateIssueDto {
    Long getCertificateConfirmationNumber();

    String getCertificateTypeCode();

    LocalDate getCertificateIssueDate();
}