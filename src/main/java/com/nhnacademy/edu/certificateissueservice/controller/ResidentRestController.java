package com.nhnacademy.edu.certificateissueservice.controller;

import com.nhnacademy.edu.certificateissueservice.dto.request.ResidentModifyRequestDto;
import com.nhnacademy.edu.certificateissueservice.dto.request.ResidentRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.Resident;
import com.nhnacademy.edu.certificateissueservice.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/residents")
public class ResidentRestController {
    private final ResidentService residentService;

    @PostMapping
    public ResponseEntity<Resident> registerResident(@RequestBody @Valid ResidentRegisterRequestDto request) {
        Resident resident = residentService.registerResident(request);
        return ResponseEntity.created(URI.create("/residents/" + resident.getResidentSerialNumber())).body(resident);
    }

    @PutMapping("/{serialNumber}")
    public ResponseEntity<Resident> modifyResident(@PathVariable Integer serialNumber,
                                                   @RequestBody @Valid ResidentModifyRequestDto request) {
        Resident resident = residentService.modifyResident(serialNumber, request);
        return ResponseEntity.ok(resident);
    }
}
