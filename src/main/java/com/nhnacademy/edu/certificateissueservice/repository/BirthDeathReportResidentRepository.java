package com.nhnacademy.edu.certificateissueservice.repository;

import com.nhnacademy.edu.certificateissueservice.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.PK> {
    @Query("select (count(b) > 0) from BirthDeathReportResident b " +
            "where b.pk.residentSerialNumber = :residentSerialNumber and b.pk.birthDeathTypeCode = :birthDeathTypeCode")
    boolean isExistsReporter(@Param("residentSerialNumber") Integer residentSerialNumber, @Param("birthDeathTypeCode") String birthDeathTypeCode);
}
