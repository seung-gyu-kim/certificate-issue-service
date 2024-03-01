package com.nhnacademy.edu.certificateissueservice.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/*
create table household_movement_address
(
   household_serial_number    int(11)      not null,
   house_movement_report_date date         not null,
   house_movement_address     varchar(500) not null,
   last_address_yn            varchar(1)   default 'Y' not null,
   primary key (household_serial_number, house_movement_report_date)
);
 */
@Getter @Setter @ToString
@NoArgsConstructor
@Entity @Table(name = "household_movement_address")
public class HouseholdMovementAddress {
    @EmbeddedId
    private PK pk;

    @Column(name = "house_movement_address", length = 500, nullable = false)
    private String houseMovementAddress;

    @Column(name = "last_address_yn", length = 1, nullable = false, columnDefinition = "varchar(1) default 'Y'")
    private String lastAddressYn;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number", nullable = false, columnDefinition = "INT(11)")
    Household household;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Setter @ToString
    @EqualsAndHashCode
    @Embeddable
    public static class PK implements Serializable {
        @Column(name = "household_serial_number", nullable = false, columnDefinition = "INT(11)")
        private Integer householdSerialNumber;

        @Column(name = "house_movement_report_date", nullable = false)
        private LocalDate houseMovementReportDate;
    }
}
