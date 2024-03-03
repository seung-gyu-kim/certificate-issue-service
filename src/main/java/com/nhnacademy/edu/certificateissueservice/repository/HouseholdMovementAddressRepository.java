package com.nhnacademy.edu.certificateissueservice.repository;

import com.nhnacademy.edu.certificateissueservice.dto.HouseholdMovementAddressDto;
import com.nhnacademy.edu.certificateissueservice.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.PK> {
    @Query("SELECT hma.houseMovementAddress AS houseMovementAddress, hma.lastAddressYn AS lastAddressYn, hma.pk.houseMovementReportDate AS houseMovementReportDate " +
            "FROM HouseholdMovementAddress AS hma " +
            "WHERE hma.pk.householdSerialNumber = (" +
            "    SELECT hcr.pk.householdSerialNumber " +
            "    FROM HouseholdCompositionResident AS hcr " +
            "    WHERE hcr.pk.residentSerialNumber = :residentSerialNumber) " +
            "ORDER BY hma.pk.houseMovementReportDate DESC")
    List<HouseholdMovementAddressDto> findHouseholdMovementAddressHistory(@Param("residentSerialNumber") Integer serialNumber);
}
