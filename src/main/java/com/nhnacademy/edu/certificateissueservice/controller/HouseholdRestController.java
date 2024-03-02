package com.nhnacademy.edu.certificateissueservice.controller;

import com.nhnacademy.edu.certificateissueservice.dto.HouseholdRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.Household;
import com.nhnacademy.edu.certificateissueservice.service.HouseholdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/household")
public class HouseholdRestController {
    private final HouseholdService householdService;

    @PostMapping
    public ResponseEntity<Household> registerHousehold(@RequestBody @Valid HouseholdRegisterRequestDto requestDto) {
        Household household = householdService.registerHousehold(requestDto);
        return ResponseEntity
                .created(URI.create(String.format("/household/%d", household.getHouseholdSerialNumber())))
                .body(household);
    }

    @DeleteMapping("/{householdSerialNumber}")
    public ResponseEntity<Void> removeHousehold(@PathVariable Integer householdSerialNumber) {
        householdService.removeHousehold(householdSerialNumber);
        return ResponseEntity.noContent().build();
    }
}
