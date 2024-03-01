package com.nhnacademy.edu.certificateissueservice.entity;

import lombok.*;
import org.hibernate.annotations.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/*
create table household_composition_resident
(
   household_serial_number                  int(11)     not null,
   resident_serial_number                   int(11)     not null,
   report_date                              date        not null,
   household_relationship_code              varchar(20) not null,
   household_composition_change_reason_code varchar(20) not null,
   primary key (household_serial_number, resident_serial_number)
);
 */
@Getter @Setter @ToString
@NoArgsConstructor
@Entity @Table(name = "household_composition_resident")
public class HouseholdCompositionResident {
    @EmbeddedId
    private PK pk;

    @Column(name = "report_date", nullable = false)
    private LocalDate reportDate;

    @Column(name = "household_relationship_code", length = 20, nullable = false)
    private String householdRelationshipCode;

    @Column(name = "household_composition_change_reason_code", length = 20, nullable = false)
    private String householdCompositionChangeReasonCode;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number", nullable = false, columnDefinition = "INT(11)")
    private Household household;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "resident_serial_number", nullable = false, columnDefinition = "INT(11)")
    private Resident resident;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Setter @ToString
    @EqualsAndHashCode
    @Embeddable
    public static class PK implements Serializable {
        @Column(name = "household_serial_number", nullable = false, columnDefinition = "INT(11)")
        private Integer householdSerialNumber;

        @Column(name = "resident_serial_number", nullable = false, columnDefinition = "INT(11)")
        private Integer residentSerialNumber;
    }
}