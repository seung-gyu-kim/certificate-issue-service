package com.nhnacademy.edu.certificateissueservice.controller;

import com.nhnacademy.edu.certificateissueservice.dto.request.DeathReportRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.BirthDeathReportResident;
import com.nhnacademy.edu.certificateissueservice.service.DeathReportResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@Controller
@RequestMapping("/residents/{serialNumber}/death")
public class DeathReportController {
    private final DeathReportResidentService deathReportResidentService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<BirthDeathReportResident> registerDeathReport(@PathVariable("serialNumber") Integer reportSerialNumber,
                                                                        @RequestBody @Valid DeathReportRequestDto requestDto) {
        BirthDeathReportResident save = deathReportResidentService.registerDeathReportResident(reportSerialNumber, requestDto);
        return ResponseEntity.created(URI.create(String.format("/residents/%d/birth/%d",
                        save.getPk().getResidentSerialNumber(),
                        save.getPk().getReportResidentSerialNumber())))
                .body(save);
    }

    @ResponseBody
    @PutMapping("/{targetSerialNumber}")
    public ResponseEntity<BirthDeathReportResident> modifyDeathReport(@PathVariable("serialNumber") Integer reportSerialNumber,
                                                                      @PathVariable Integer targetSerialNumber,
                                                                      @RequestBody @Valid DeathReportRequestDto requestDto) {
        BirthDeathReportResident birthDeathReportResident = deathReportResidentService.modifyDeathReportResident(reportSerialNumber, targetSerialNumber, requestDto);
        return ResponseEntity.ok(birthDeathReportResident);
    }

    @ResponseBody
    @DeleteMapping("/{targetSerialNumber}")
    public ResponseEntity<Void> removeDeathReport(@PathVariable("serialNumber") Integer reportSerialNumber,
                                                  @PathVariable Integer targetSerialNumber) {
        deathReportResidentService.removeDeathReportResident(reportSerialNumber, targetSerialNumber);
        return ResponseEntity.noContent().build();
    }
}
