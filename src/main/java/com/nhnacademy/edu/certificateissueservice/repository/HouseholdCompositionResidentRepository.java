package com.nhnacademy.edu.certificateissueservice.repository;

import com.nhnacademy.edu.certificateissueservice.entity.HouseholdCompositionResident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseholdCompositionResidentRepository extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.PK> {
    Optional<HouseholdCompositionResident> findByResident_ResidentSerialNumber(Integer residentSerialNumber);

    long deleteAllByPk_ResidentSerialNumber(Integer residentSerialNumber);
}
