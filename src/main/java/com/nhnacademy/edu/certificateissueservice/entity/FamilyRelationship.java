package com.nhnacademy.edu.certificateissueservice.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/*
create table family_relationship
(
   base_resident_serial_number   int(11)     not null,
   family_resident_serial_number int(11)     not null,
   family_relationship_code      varchar(20) not null,
   primary key (base_resident_serial_number, family_resident_serial_number)
);
 */
@Getter @Setter @ToString
@NoArgsConstructor
@Entity @Table(name = "family_relationship")
public class FamilyRelationship {
    @EmbeddedId
    private PK pk;

    @Column(name = "family_relationship_code", length = 20, nullable = false)
    private String familyRelationshipCode;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "base_resident_serial_number", nullable = false, columnDefinition = "INT(11)")
    private Resident baseResident;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter @Setter @ToString
    @EqualsAndHashCode
    @Embeddable
    public static class PK implements Serializable {
        @Column(name = "base_resident_serial_number", nullable = false, columnDefinition = "INT(11)")
        private Integer baseResidentSerialNumber;

        @Column(name = "family_resident_serial_number", nullable = false, columnDefinition = "INT(11)")
        private Integer familyResidentSerialNumber;
    }
}
