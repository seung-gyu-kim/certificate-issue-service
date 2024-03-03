package com.nhnacademy.edu.certificateissueservice.controller;

import com.nhnacademy.edu.certificateissueservice.dto.BirthReportRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.BirthDeathReportResident;
import com.nhnacademy.edu.certificateissueservice.service.BirthDeathReportResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@Controller
@RequestMapping("/residents/{serialNumber}/birth")
public class BirthReportController {
    private final BirthDeathReportResidentService birthDeathReportResidentService;

    @ResponseBody
    @PostMapping
    public ResponseEntity<BirthDeathReportResident> registerBirthReport(@PathVariable("serialNumber") Integer reportSerialNumber,
                                                                        @RequestBody BirthReportRequestDto birthDeathReportRegisterRequestDto) {
        BirthDeathReportResident save = birthDeathReportResidentService.registerBirthReportResident(reportSerialNumber, birthDeathReportRegisterRequestDto);
        return ResponseEntity.created(URI.create(String.format("/residents/%d/birth/%d",
                        save.getPk().getResidentSerialNumber(),
                        save.getPk().getReportResidentSerialNumber())))
                .body(save);
    }

    @ResponseBody
    @PutMapping("/{targetSerialNumber}")
    public ResponseEntity<BirthDeathReportResident> modifyBirthReport(@PathVariable("serialNumber") Integer reportSerialNumber,
                                                                      @PathVariable Integer targetSerialNumber,
                                                                      @RequestBody @Valid BirthReportRequestDto requestDto) {
        BirthDeathReportResident birthDeathReportResident = birthDeathReportResidentService.modifyBirthDeathReportResident(reportSerialNumber, targetSerialNumber, requestDto);
        return ResponseEntity.ok(birthDeathReportResident);
    }
//
    @ResponseBody
    @DeleteMapping("/{targetSerialNumber}")
    public ResponseEntity<Void> deleteBirthReport(@PathVariable("serialNumber") Integer reportSerialNumber,
                                                  @PathVariable Integer targetSerialNumber) {
        birthDeathReportResidentService.deleteBirthDeathReportResident(reportSerialNumber, targetSerialNumber);
        return ResponseEntity.noContent().build();
    }
}
