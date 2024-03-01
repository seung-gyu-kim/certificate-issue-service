package com.nhnacademy.edu.certificateissueservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/*
create table resident
(
   resident_serial_number       int(11)      not null,
   name                         varchar(100) not null,
   resident_registration_number varchar(300) not null,
   gender_code                  varchar(20)  not null,
   birth_date                   datetime     not null,
   birth_place_code             varchar(20)  not null,
   registration_base_address    varchar(500) not null,
   death_date                   datetime     null,
   death_place_code             varchar(20)  null,
   death_place_address          varchar(500) null,
   primary key (resident_serial_number)
);
 */
@Getter @Setter @ToString
@NoArgsConstructor
@Entity @Table(name = "resident")
public class Resident {
    @Id
    @Column(name = "resident_serial_number", nullable = false, columnDefinition = "INT(11)")
    private Integer residentSerialNumber;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "resident_registration_number", length = 300, nullable = false)
    private String residentRegistrationNumber;

    @Column(name = "gender_code", length = 20, nullable = false)
    private String genderCode;

    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @Column(name = "birth_place_code", length = 20, nullable = false)
    private String birthPlaceCode;

    @Column(name = "registration_base_address", length = 500, nullable = false)
    private String registrationBaseAddress;

    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "death_place_code", length = 20)
    private String deathPlaceCode;

    @Column(name = "death_place_address", length = 500)
    private String deathPlaceAddress;
}
