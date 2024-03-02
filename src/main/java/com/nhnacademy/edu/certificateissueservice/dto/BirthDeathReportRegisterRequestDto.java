package com.nhnacademy.edu.certificateissueservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BirthDeathReportRegisterRequestDto {
    @NotNull
    private Integer targetSerialNumber;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate birthDeathReportDate;
    @NotNull
    private String reportQualificationsCode;
    @NotNull
    private String emailAddress;
    @NotNull
    private String phoneNumber;
}
