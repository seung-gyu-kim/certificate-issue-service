package com.nhnacademy.edu.certificateissueservice.service;

import com.nhnacademy.edu.certificateissueservice.dto.response.ResidentReportResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IndexQueryService {
    Page<ResidentReportResponseDto> getResidentReport(Pageable pageable);
}
