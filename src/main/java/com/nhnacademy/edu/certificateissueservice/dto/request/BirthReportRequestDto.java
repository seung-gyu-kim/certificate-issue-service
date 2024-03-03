package com.nhnacademy.edu.certificateissueservice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhnacademy.edu.certificateissueservice.enums.BirthReportQualificationsCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BirthReportRequestDto {
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate birthReportDate;
    @NotNull
    private TargetResident targetResident;
    private ParentResident fatherResident;
    private ParentResident motherResident;
    @NotNull
    private ReportResident reportResident;

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class TargetResident {
        @NotNull
        private String name;
        @NotNull
        private String genderCode;
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss")
        private LocalDateTime birthDate;
        @NotNull
        private String birthPlaceCode;
        @NotNull
        private String registrationBaseAddress;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class ParentResident {
        @NotNull
        private String name;
        @NotNull
        private String residentRegistrationNumber;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class ReportResident {
        @NotNull
        private String name;
        @NotNull
        private String residentRegistrationNumber;
        @NotNull
        private BirthReportQualificationsCode birthReportQualificationsCode;
        private String emailAddress;
        @NotNull
        private String phoneNumber;
    }
}
