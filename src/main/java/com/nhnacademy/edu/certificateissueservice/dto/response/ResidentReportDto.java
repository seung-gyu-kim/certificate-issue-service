package com.nhnacademy.edu.certificateissueservice.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidentReportDto {
    private Integer residentSerialNumber;
    private String name;
    private boolean hasBirthReport;
    private boolean hasDeathReport;
}
