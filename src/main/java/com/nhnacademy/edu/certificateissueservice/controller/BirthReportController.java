package com.nhnacademy.edu.certificateissueservice.controller;

import com.nhnacademy.edu.certificateissueservice.dto.request.BirthReportRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.BirthDeathReportResident;
import com.nhnacademy.edu.certificateissueservice.service.BirthReportResidentService;
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
    private final BirthReportResidentService birthReportResidentService;

    @ResponseBody
    @PostMapping
    public ResponseEntity<BirthDeathReportResident> registerBirthReport(@PathVariable("serialNumber") Integer reportSerialNumber,
                                                                        @RequestBody BirthReportRequestDto birthDeathReportRegisterRequestDto) {
        BirthDeathReportResident save = birthReportResidentService.registerBirthReportResident(reportSerialNumber, birthDeathReportRegisterRequestDto);
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
        BirthDeathReportResident birthDeathReportResident = birthReportResidentService.modifyBirthReportResident(reportSerialNumber, targetSerialNumber, requestDto);
        return ResponseEntity.ok(birthDeathReportResident);
    }

    @ResponseBody
    @DeleteMapping("/{targetSerialNumber}")
    public ResponseEntity<Void> removeBirthReport(@PathVariable("serialNumber") Integer reportSerialNumber,
                                                  @PathVariable Integer targetSerialNumber) {
        birthReportResidentService.removeBirthReportResident(reportSerialNumber, targetSerialNumber);
        return ResponseEntity.noContent().build();
    }
}
