package com.nhnacademy.edu.certificateissueservice.repository;

import com.nhnacademy.edu.certificateissueservice.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.PK>, FamilyRelationshipRepositoryCustom {
    long deleteAllByPk_BaseResidentSerialNumber(Integer baseResidentSerialNumber);
}
