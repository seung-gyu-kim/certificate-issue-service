package com.nhnacademy.edu.certificateissueservice.exception;

import com.nhnacademy.edu.certificateissueservice.entity.FamilyRelationship;

public class FamilyRelationshipNotFoundException extends RuntimeException {
    public FamilyRelationshipNotFoundException(FamilyRelationship.PK pk) {
        super(String.format("가족관계를 찾을 수 없습니다. (기준주민일련번호: %d, 대상주민일련번호: %d)",
                pk.getBaseResidentSerialNumber(), pk.getFamilyResidentSerialNumber()));
    }
}
