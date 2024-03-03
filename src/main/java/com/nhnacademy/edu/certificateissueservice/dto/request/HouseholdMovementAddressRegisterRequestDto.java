package com.nhnacademy.edu.certificateissueservice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDate;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdMovementAddressRegisterRequestDto {
    @Null
    private Integer householdSerialNumber;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate houseMovementReportDate;
    @NotNull
    private String houseMovementAddress;
    @NotNull
    private String lastAddressYn;
}
