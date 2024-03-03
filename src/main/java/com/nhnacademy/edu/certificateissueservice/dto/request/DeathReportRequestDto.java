package com.nhnacademy.edu.certificateissueservice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhnacademy.edu.certificateissueservice.enums.DeathReportQualificationsCode;
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
public class DeathReportRequestDto {
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate deathReportDate;
    @NotNull
    private TargetResident targetResident;
    @NotNull
    private ReportResident reportResident;

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class TargetResident {
        @NotNull
        private String name;
        @NotNull
        private String residentRegistrationNumber;
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss")
        private LocalDateTime deathDate;
        @NotNull
        private String deathPlaceCode;
        @NotNull
        private String deathPlaceAddress;
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
        private DeathReportQualificationsCode deathBirthReportQualificationsCode;
        private String emailAddress;
        @NotNull
        private String phoneNumber;
    }
}
