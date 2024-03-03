package com.nhnacademy.edu.certificateissueservice.controller;

import com.nhnacademy.edu.certificateissueservice.dto.request.HouseholdMovementAddressModifyRequestDto;
import com.nhnacademy.edu.certificateissueservice.dto.request.HouseholdMovementAddressRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.HouseholdMovementAddress;
import com.nhnacademy.edu.certificateissueservice.service.HouseholdMovementAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@RestController
@RequestMapping("/household/{householdSerialNumber}/movement")
public class HouseholdMovementAddressRestController {
    private final HouseholdMovementAddressService householdMovementAddressService;

    @PostMapping
    public ResponseEntity<HouseholdMovementAddress> registerHouseholdMovementAddressResponseEntity(@PathVariable Integer householdSerialNumber,
                                                                                                   @RequestBody @Valid HouseholdMovementAddressRegisterRequestDto requestDto) {
        requestDto.setHouseholdSerialNumber(householdSerialNumber);
        HouseholdMovementAddress householdMovementAddress = householdMovementAddressService.registerHouseholdMovementAddress(requestDto);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return ResponseEntity
                .created(URI.create(String.format(
                        "/household/%d/movement/%s",
                        householdMovementAddress.getPk().getHouseholdSerialNumber(),
                        householdMovementAddress.getPk().getHouseMovementReportDate().format(formatter))))
                .body(householdMovementAddress);
    }

    @PutMapping("/{reportDate}")
    public ResponseEntity<HouseholdMovementAddress> modifyHouseholdMovementAddressResponseEntity(@PathVariable Integer householdSerialNumber,
                                                                                                 @PathVariable @DateTimeFormat(pattern = "yyyyMMdd") LocalDate reportDate,
                                                                                                 @RequestBody @Valid HouseholdMovementAddressModifyRequestDto requestDto) {
        requestDto.setHouseholdSerialNumber(householdSerialNumber);
        requestDto.setHouseMovementReportDate(reportDate);
        HouseholdMovementAddress householdMovementAddress = householdMovementAddressService.modifyHouseholdMovementAddress(requestDto);
        return ResponseEntity.ok(householdMovementAddress);
    }

    @DeleteMapping("/{reportDate}")
    public ResponseEntity<Void> removeHouseholdMovementAddressVoidResponseEntity(@PathVariable Integer householdSerialNumber,
                                                                                 @PathVariable @DateTimeFormat(pattern = "yyyyMMdd") LocalDate reportDate) {
        householdMovementAddressService.removeHouseholdMovementAddress(householdSerialNumber, reportDate);
        return ResponseEntity.noContent().build();
    }
}
