package com.nhnacademy.edu.certificateissueservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Null;
import java.time.LocalDate;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdMovementAddressModifyRequestDto {
    @Null
    private Integer householdSerialNumber;
    @Null
    private LocalDate houseMovementReportDate;
    private String houseMovementAddress;
    private String lastAddressYn;
}
