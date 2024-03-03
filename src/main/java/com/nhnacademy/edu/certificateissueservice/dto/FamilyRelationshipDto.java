package com.nhnacademy.edu.certificateissueservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class FamilyRelationshipDto {
    private String familyRelationshipCode;
    private String name;
    private LocalDateTime birthDate;
    private String residentRegistrationNumber;
    private String genderCode;

    public String getResidentRegistrationNumber() {
        return residentRegistrationNumber.substring(0, 6) + "-*******";
    }
}
