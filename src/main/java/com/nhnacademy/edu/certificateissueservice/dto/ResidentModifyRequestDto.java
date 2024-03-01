package com.nhnacademy.edu.certificateissueservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResidentModifyRequestDto {
    private String name;
    private String residentRegistrationNumber;
    private String genderCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss")
    private LocalDateTime birthDate;
    private String birthPlaceCode;
    private String registrationBaseAddress;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss")
    private LocalDateTime deathDate;
    private String deathPlaceCode;
    private String deathPlaceAddress;
}