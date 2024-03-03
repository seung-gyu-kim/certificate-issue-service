package com.nhnacademy.edu.certificateissueservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CertificateIssueService {
    Page<?> getCertificateIssueList(Integer serialNumber, Pageable pageable);
}
