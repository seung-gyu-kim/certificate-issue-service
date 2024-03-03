package com.nhnacademy.edu.certificateissueservice.repository;

import com.nhnacademy.edu.certificateissueservice.dto.CertificateIssueDto;
import com.nhnacademy.edu.certificateissueservice.entity.CertificateIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
    Page<CertificateIssueDto> findByResident_ResidentSerialNumber(Integer residentSerialNumber, Pageable pageable);

}
