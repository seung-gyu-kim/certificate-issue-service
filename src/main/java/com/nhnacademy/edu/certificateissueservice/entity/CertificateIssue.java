package com.nhnacademy.edu.certificateissueservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

/*
create table certificate_issue
(
   certificate_confirmation_number bigint      not null,
   resident_serial_number          int         not null,
   certificate_type_code           varchar(20) not null,
   certificate_issue_date          date        not null,
   primary key (certificate_confirmation_number)
);
 */
@Getter @Setter @ToString
@NoArgsConstructor
@Entity @Table(name = "certificate_issue")
public class CertificateIssue {
    @Id
    @Column(name = "certificate_confirmation_number", nullable = false)
    private Long certificateConfirmationNumber;

    @ManyToOne
    @JoinColumn(name = "resident_serial_number", nullable = false)
    private Resident resident;

    @Column(name = "certificate_type_code", length = 20, nullable = false)
    private String certificateTypeCode;

    @Column(name = "certificate_issue_date", nullable = false)
    private LocalDate certificateIssueDate;
}