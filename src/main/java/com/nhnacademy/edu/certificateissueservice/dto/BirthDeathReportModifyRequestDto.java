package com.nhnacademy.edu.certificateissueservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Null;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BirthDeathReportModifyRequestDto {
    @Null
    private Integer reportResidentSerialNumber;
    @Null
    private Boolean isBirthReport;
    @Null
    private Integer targetSerialNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate birthDeathReportDate;
    private String reportQualificationsCode;
    private String emailAddress;
    private String phoneNumber;
}
