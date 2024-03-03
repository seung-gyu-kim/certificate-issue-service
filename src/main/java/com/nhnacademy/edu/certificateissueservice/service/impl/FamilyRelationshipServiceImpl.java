package com.nhnacademy.edu.certificateissueservice.service.impl;

import com.nhnacademy.edu.certificateissueservice.dto.request.RelationshipModifyRequestDto;
import com.nhnacademy.edu.certificateissueservice.dto.request.RelationshipRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.FamilyRelationship;
import com.nhnacademy.edu.certificateissueservice.entity.Resident;
import com.nhnacademy.edu.certificateissueservice.exception.FamilyRelationshipNotFoundException;
import com.nhnacademy.edu.certificateissueservice.exception.ResidentNotFoundException;
import com.nhnacademy.edu.certificateissueservice.repository.FamilyRelationshipRepository;
import com.nhnacademy.edu.certificateissueservice.repository.ResidentRepository;
import com.nhnacademy.edu.certificateissueservice.service.FamilyRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;

    @Override
    public FamilyRelationship registerFamilyRelationship(Integer serialNumber, RelationshipRegisterRequestDto relationshipRegisterRequestDto) {
        Resident baseResident = residentRepository.findById(serialNumber)
                .orElseThrow(() -> new ResidentNotFoundException(serialNumber));

        FamilyRelationship familyRelationship = new FamilyRelationship();
        familyRelationship.setPk(new FamilyRelationship.PK(
                baseResident.getResidentSerialNumber(),
                relationshipRegisterRequestDto.getFamilySerialNumber()));
        familyRelationship.setFamilyRelationshipCode(relationshipRegisterRequestDto.getRelationShip().getCode());
        familyRelationship.setBaseResident(baseResident);
        return familyRelationshipRepository.save(familyRelationship);
    }

    @Override
    public FamilyRelationship modifyFamilyRelationship(Integer serialNumber, Integer familySerialNumber, RelationshipModifyRequestDto relationshipModifyRequestDto) {
        FamilyRelationship.PK pk = new FamilyRelationship.PK(serialNumber, familySerialNumber);
        FamilyRelationship familyRelationship = familyRelationshipRepository.findById(pk)
                .orElseThrow(() -> new FamilyRelationshipNotFoundException(pk));

        familyRelationship.setFamilyRelationshipCode(relationshipModifyRequestDto.getRelationShip().getCode());
        return familyRelationship;
    }

    @Override
    public void removeFamilyRelationship(Integer serialNumber, Integer familySerialNumber) {
        familyRelationshipRepository.deleteById(new FamilyRelationship.PK(serialNumber, familySerialNumber));
    }
}
