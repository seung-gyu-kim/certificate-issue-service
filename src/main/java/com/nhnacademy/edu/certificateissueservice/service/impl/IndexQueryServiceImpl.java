package com.nhnacademy.edu.certificateissueservice.service.impl;

import com.nhnacademy.edu.certificateissueservice.dto.ResidentSNAndNameDto;
import com.nhnacademy.edu.certificateissueservice.dto.response.ResidentReportDto;
import com.nhnacademy.edu.certificateissueservice.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.edu.certificateissueservice.repository.ResidentRepository;
import com.nhnacademy.edu.certificateissueservice.service.IndexQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class IndexQueryServiceImpl implements IndexQueryService {
    private final ResidentRepository residentRepository;
    private final BirthDeathReportResidentRepository reportResidentRepository;

    @Override
    public Page<ResidentReportDto> getResidentReport(Pageable pageable) {
        List<ResidentReportDto> residentReportList = new ArrayList<>();
        Page<ResidentSNAndNameDto> residentPage = residentRepository.getAllBy(pageable);
        for(ResidentSNAndNameDto resident : residentPage.getContent()) {
            ResidentReportDto residentReportDto = new ResidentReportDto();
            residentReportDto.setResidentSerialNumber(resident.getResidentSerialNumber());
            residentReportDto.setName(resident.getName());
            residentReportDto.setHasBirthReport(reportResidentRepository.isExistsReporter(resident.getResidentSerialNumber(), "출생"));
            residentReportDto.setHasDeathReport(reportResidentRepository.isExistsReporter(resident.getResidentSerialNumber(), "사망"));
            residentReportList.add(residentReportDto);
        }
        Page<ResidentReportDto> residentReportPage = new PageImpl<>(residentReportList, pageable, residentPage.getTotalElements());
        return residentReportPage;
    }
}
