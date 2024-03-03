package com.nhnacademy.edu.certificateissueservice.controller;

import com.nhnacademy.edu.certificateissueservice.dto.request.RelationshipModifyRequestDto;
import com.nhnacademy.edu.certificateissueservice.dto.request.RelationshipRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.FamilyRelationship;
import com.nhnacademy.edu.certificateissueservice.service.FamilyRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/residents/{serialNumber}/relationship")
public class FamilyRelationshipRestController {
    private final FamilyRelationshipService familyRelationshipService;

    @PostMapping
    public ResponseEntity<FamilyRelationship> registerFamilyRelationship(@PathVariable Integer serialNumber,
                                                                         @RequestBody @Valid RelationshipRegisterRequestDto relationshipRegisterRequestDto) {
        FamilyRelationship familyRelationship = familyRelationshipService.registerFamilyRelationship(serialNumber, relationshipRegisterRequestDto);
        return ResponseEntity
                .created(URI.create(String.format("/residents/%d/relationship/%d", familyRelationship.getPk().getBaseResidentSerialNumber(), familyRelationship.getPk().getFamilyResidentSerialNumber())))
                .body(familyRelationship);
    }

    @PutMapping("/{familySerialNumber}")
    public ResponseEntity<FamilyRelationship> modifyFamilyRelationship(@PathVariable Integer serialNumber,
                                                                       @PathVariable Integer familySerialNumber,
                                                                       @RequestBody @Valid RelationshipModifyRequestDto relationshipModifyRequestDto) {
        FamilyRelationship familyRelationship = familyRelationshipService.modifyFamilyRelationship(serialNumber, familySerialNumber, relationshipModifyRequestDto);
        return ResponseEntity.ok(familyRelationship);
    }

    @DeleteMapping("/{familySerialNumber}")
    public ResponseEntity<Void> removeFamilyRelationship(@PathVariable Integer serialNumber,
                                                                       @PathVariable Integer familySerialNumber) {
        familyRelationshipService.removeFamilyRelationship(serialNumber, familySerialNumber);
        return ResponseEntity.noContent().build();
    }
}