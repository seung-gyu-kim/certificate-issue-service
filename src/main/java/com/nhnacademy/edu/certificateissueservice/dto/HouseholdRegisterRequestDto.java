package com.nhnacademy.edu.certificateissueservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdRegisterRequestDto {
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate householdCompositionDate;
    @NotNull
    private String householdCompositionReasonCode;
    @NotNull
    private String currentHouseMovementAddress;
    @NotNull
    private Integer householdResidentSerialNumber;
}
