package com.nhnacademy.edu.certificateissueservice.enums;

public enum FamilyRelationShipCode {
    father("부"),
    mother("모"),
    spouse("배우자"),
    child("자녀");

    private String code;

    FamilyRelationShipCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
