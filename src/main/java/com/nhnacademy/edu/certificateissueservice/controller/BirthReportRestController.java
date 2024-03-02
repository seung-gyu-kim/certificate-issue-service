package com.nhnacademy.edu.certificateissueservice.controller;

import com.nhnacademy.edu.certificateissueservice.dto.BirthDeathReportModifyRequestDto;
import com.nhnacademy.edu.certificateissueservice.dto.BirthDeathReportRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.BirthDeathReportResident;
import com.nhnacademy.edu.certificateissueservice.service.BirthDeathReportResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/residents/{serialNumber}/birth")
public class BirthReportRestController {
    private final BirthDeathReportResidentService birthDeathReportResidentService;

    @PostMapping
    public ResponseEntity<BirthDeathReportResident> registerBirthReport(@PathVariable("serialNumber") Integer reportSerialNumber,
                                                                        @RequestBody @Valid BirthDeathReportRegisterRequestDto request) {
        BirthDeathReportResident save = birthDeathReportResidentService.registerBirthReport(reportSerialNumber, request);
        return ResponseEntity.created(URI.create(String.format("/residents/%d/birth/%d",
                        save.getPk().getResidentSerialNumber(),
                        save.getPk().getReportResidentSerialNumber())))
                .body(save);
    }


    @PutMapping("/{targetSerialNumber}")
    public ResponseEntity<BirthDeathReportResident> modifyBirthReport(@PathVariable("serialNumber") Integer reportSerialNumber,
                                                                      @PathVariable Integer targetSerialNumber,
                                                                      @RequestBody @Valid BirthDeathReportModifyRequestDto request) {
        BirthDeathReportResident birthDeathReportResident = birthDeathReportResidentService.modifyBirthReport(reportSerialNumber, targetSerialNumber, request);
        return ResponseEntity.ok(birthDeathReportResident);
    }

    @DeleteMapping("/{targetSerialNumber}")
    public ResponseEntity<Void> deleteBirthReport(@PathVariable("serialNumber") Integer reportSerialNumber,
                                                  @PathVariable Integer targetSerialNumber) {
        birthDeathReportResidentService.deleteBirthReport(reportSerialNumber, targetSerialNumber);
        return ResponseEntity.noContent().build();
    }
}
