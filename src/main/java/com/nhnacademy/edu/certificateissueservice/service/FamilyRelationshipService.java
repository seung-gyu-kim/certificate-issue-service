package com.nhnacademy.edu.certificateissueservice.service;

import com.nhnacademy.edu.certificateissueservice.dto.request.RelationshipModifyRequestDto;
import com.nhnacademy.edu.certificateissueservice.dto.request.RelationshipRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.FamilyRelationship;

public interface FamilyRelationshipService {
    FamilyRelationship registerFamilyRelationship(Integer serialNumber,
                                                  RelationshipRegisterRequestDto relationshipRegisterRequestDto);

    FamilyRelationship modifyFamilyRelationship(Integer serialNumber, Integer familySerialNumber,
                                                RelationshipModifyRequestDto relationshipModifyRequestDto);

    void removeFamilyRelationship(Integer serialNumber, Integer familySerialNumber);
}
