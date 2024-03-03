package com.nhnacademy.edu.certificateissueservice.exception;

public class HouseholdNotFoundException extends RuntimeException {
    public HouseholdNotFoundException(Integer householdSerialNumber) {
        super(String.format("세대를 찾을 수 없습니다. (세대일련번호: %d)", householdSerialNumber));
    }

    public HouseholdNotFoundException() {
        super("세대를 찾을 수 없습니다.");
    }
}
