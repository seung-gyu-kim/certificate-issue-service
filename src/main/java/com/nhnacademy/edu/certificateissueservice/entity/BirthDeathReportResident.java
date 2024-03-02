package com.nhnacademy.edu.certificateissueservice.entity;

import com.nhnacademy.edu.certificateissueservice.dto.BirthDeathReportRegisterRequestDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/*
create table birth_death_report_resident
(
   resident_serial_number           int(11)     not null,
   birth_death_type_code            varchar(2) not null,
   report_resident_serial_number    int(11)     not null,
   birth_death_report_date          date        not null,
   birth_report_qualifications_code varchar(20) null,
   death_report_qualifications_code varchar(20) null,
   email_address                    varchar(50) null,
   phone_number                     varchar(20) not null,
   primary key (resident_serial_number, birth_death_type_code, report_resident_serial_number)
);
 */
@Getter @Setter @ToString
@NoArgsConstructor
@Entity @Table(name = "birth_death_report_resident")
public class BirthDeathReportResident {
    @EmbeddedId
    private PK pk;

    @Column(name = "birth_death_report_date", nullable = false)
    private LocalDate birthDeathReportDate;

    @Column(name = "birth_report_qualifications_code", length = 20)
    private String birthReportQualificationsCode;

    @Column(name = "death_report_qualifications_code", length = 20)
    private String deathReportQualificationsCode;

    @Column(name = "email_address", length = 50)
    private String emailAddress;

    @Column(name = "phone_number", length = 20, nullable = false)
    private String phoneNumber;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number", nullable = false, columnDefinition = "INT(11)")
    Resident resident;

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Getter @Setter @ToString
    @Embeddable
    public static class PK implements Serializable {
        @Column(name = "resident_serial_number", nullable = false, columnDefinition = "INT(11)")
        private Integer residentSerialNumber;

        @Column(name = "birth_death_type_code", length = 2, nullable = false)
        private String birthDeathTypeCode;

        @Column(name = "report_resident_serial_number", nullable = false, columnDefinition = "INT(11)")
        private Integer reportResidentSerialNumber;
    }

    public static BirthDeathReportResident of(BirthDeathReportRegisterRequestDto request, BirthDeathReportResident.PK pk, Resident resident) {
        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        birthDeathReportResident.setPk(pk);
        birthDeathReportResident.setBirthDeathReportDate(request.getBirthDeathReportDate());
        if(Boolean.TRUE.equals(request.getIsBirthReport())) {
            birthDeathReportResident.setBirthReportQualificationsCode(request.getReportQualificationsCode());
        } else {
            birthDeathReportResident.setDeathReportQualificationsCode(request.getReportQualificationsCode());
        }
        birthDeathReportResident.setEmailAddress(request.getEmailAddress());
        birthDeathReportResident.setPhoneNumber(request.getPhoneNumber());
        birthDeathReportResident.setResident(resident);
        return birthDeathReportResident;
    }
}
