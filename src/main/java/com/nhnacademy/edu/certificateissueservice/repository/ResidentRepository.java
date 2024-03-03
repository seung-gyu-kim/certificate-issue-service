package com.nhnacademy.edu.certificateissueservice.repository;

import com.nhnacademy.edu.certificateissueservice.dto.ResidentSNAndNameDto;
import com.nhnacademy.edu.certificateissueservice.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Integer> {
    Optional<Resident> findByNameAndResidentRegistrationNumber(String name, String residentRegistrationNumber);
    Page<ResidentSNAndNameDto> getAllBy(Pageable pageable);
}
