package com.nhnacademy.edu.certificateissueservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

/*
create table household
(
   household_serial_number           int(11)      not null,
   household_resident_serial_number  int(11)      not null,
   household_composition_date        date         not null,
   household_composition_reason_code varchar(20)  not null,
   current_house_movement_address    varchar(500) not null,
   primary key (household_serial_number)
);
 */
@Getter @Setter @ToString
@NoArgsConstructor
@Entity @Table(name = "household")
public class Household {
    @Id
    @Column(name = "household_serial_number", nullable = false, columnDefinition = "INT(11)")
    private Integer householdSerialNumber;

    @Column(name = "household_composition_date", nullable = false)
    private LocalDate householdCompositionDate;

    @Column(name = "household_composition_reason_code", length = 20, nullable = false)
    private String householdCompositionReasonCode;

    @Column(name = "current_house_movement_address", length = 500, nullable = false)
    private String currentHouseMovementAddress;

    @ManyToOne
    @JoinColumn(name = "household_resident_serial_number", nullable = false, columnDefinition = "INT(11)")
    private Resident householdResident;
}