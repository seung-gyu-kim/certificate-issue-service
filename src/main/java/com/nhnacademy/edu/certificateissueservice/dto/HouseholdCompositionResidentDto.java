package com.nhnacademy.edu.certificateissueservice.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class HouseholdCompositionResidentDto {
    private String householdRelationshipCode;
    private String name;
    private String residentRegistrationNumber;
    private LocalDate reportDate;
    private String householdCompositionChangeReasonCode;

    public String getResidentRegistrationNumber() {
        return residentRegistrationNumber.substring(0, 6) + "-*******";
    }
}
