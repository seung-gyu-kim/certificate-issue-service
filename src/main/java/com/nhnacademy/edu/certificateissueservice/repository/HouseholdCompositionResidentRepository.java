package com.nhnacademy.edu.certificateissueservice.repository;

import com.nhnacademy.edu.certificateissueservice.dto.HouseholdCompositionResidentDto;
import com.nhnacademy.edu.certificateissueservice.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HouseholdCompositionResidentRepository extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.PK> {
    Optional<HouseholdCompositionResident> findByResident_ResidentSerialNumber(Integer residentSerialNumber);

    long deleteAllByPk_ResidentSerialNumber(Integer residentSerialNumber);

    @Query("SELECT new com.nhnacademy.edu.certificateissueservice.dto.HouseholdCompositionResidentDto(hcr.householdRelationshipCode, r.name, r.residentRegistrationNumber, hcr.reportDate, hcr.householdCompositionChangeReasonCode) " +
            "FROM HouseholdCompositionResident hcr " +
            "INNER JOIN hcr.resident r " +
            "WHERE hcr.pk.householdSerialNumber = (" +
            "    SELECT hcr2.pk.householdSerialNumber " +
            "    FROM HouseholdCompositionResident hcr2 " +
            "    WHERE hcr2.pk.residentSerialNumber = :residentSerialNumber)")
    List<HouseholdCompositionResidentDto> getHouseholdCompositionResidents(@Param("residentSerialNumber") Integer serialNumber);
}
