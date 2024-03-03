package com.nhnacademy.edu.certificateissueservice.controller;

import com.nhnacademy.edu.certificateissueservice.service.BirthDeathReportResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/residents/{serialNumber}/death")
public class DeathReportRestController {
    private final BirthDeathReportResidentService birthDeathReportResidentService;

//    @PostMapping
//    public ResponseEntity<BirthDeathReportResident> registerBirthReport(@PathVariable("serialNumber") Integer reportSerialNumber,
//                                                                        @RequestBody @Valid BirthDeathReportRegisterRequestDto birthDeathReportRegisterRequestDto) {
//        birthDeathReportRegisterRequestDto.setIsBirthReport(false);
//        birthDeathReportRegisterRequestDto.setReportResidentSerialNumber(reportSerialNumber);
//        BirthDeathReportResident save = birthDeathReportResidentService.registerBirthDeathReportResident(birthDeathReportRegisterRequestDto);
//        return ResponseEntity.created(URI.create(String.format("/residents/%d/birth/%d",
//                        save.getPk().getResidentSerialNumber(),
//                        save.getPk().getReportResidentSerialNumber())))
//                .body(save);
//    }
//
//
//    @PutMapping("/{targetSerialNumber}")
//    public ResponseEntity<BirthDeathReportResident> modifyBirthReport(@PathVariable("serialNumber") Integer reportSerialNumber,
//                                                                      @PathVariable Integer targetSerialNumber,
//                                                                      @RequestBody @Valid BirthDeathReportModifyRequestDto birthDeathReportModifyRequestDto) {
//        birthDeathReportModifyRequestDto.setIsBirthReport(false);
//        birthDeathReportModifyRequestDto.setReportResidentSerialNumber(reportSerialNumber);
//        birthDeathReportModifyRequestDto.setTargetSerialNumber(targetSerialNumber);
//        BirthDeathReportResident birthDeathReportResident = birthDeathReportResidentService.modifyBirthDeathReportResident(birthDeathReportModifyRequestDto);
//        return ResponseEntity.ok(birthDeathReportResident);
//    }
//
//    @DeleteMapping("/{targetSerialNumber}")
//    public ResponseEntity<Void> deleteBirthReport(@PathVariable("serialNumber") Integer reportSerialNumber,
//                                                  @PathVariable Integer targetSerialNumber) {
//        birthDeathReportResidentService.deleteBirthDeathReportResident(reportSerialNumber, targetSerialNumber, false);
//        return ResponseEntity.noContent().build();
//    }
}
