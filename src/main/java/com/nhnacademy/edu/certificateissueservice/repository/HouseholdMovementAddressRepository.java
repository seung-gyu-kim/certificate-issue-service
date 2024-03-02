package com.nhnacademy.edu.certificateissueservice.repository;

import com.nhnacademy.edu.certificateissueservice.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.PK> {
}
