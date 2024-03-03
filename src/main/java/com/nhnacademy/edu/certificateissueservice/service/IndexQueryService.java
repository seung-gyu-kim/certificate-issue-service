package com.nhnacademy.edu.certificateissueservice.service;

import com.nhnacademy.edu.certificateissueservice.dto.response.ResidentReportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IndexQueryService {
    Page<ResidentReportDto> getResidentReport(Pageable pageable);
}
