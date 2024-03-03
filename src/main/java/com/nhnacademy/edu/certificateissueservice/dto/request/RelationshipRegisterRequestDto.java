package com.nhnacademy.edu.certificateissueservice.dto.request;

import com.nhnacademy.edu.certificateissueservice.enums.FamilyRelationShipCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipRegisterRequestDto {
    @NotNull
    private Integer familySerialNumber;
    @NotNull
    private FamilyRelationShipCode relationShip;
}
