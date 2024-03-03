package com.nhnacademy.edu.certificateissueservice.repository.impl;

import com.nhnacademy.edu.certificateissueservice.dto.FamilyRelationshipDto;
import com.nhnacademy.edu.certificateissueservice.entity.FamilyRelationship;
import com.nhnacademy.edu.certificateissueservice.entity.QFamilyRelationship;
import com.nhnacademy.edu.certificateissueservice.entity.QResident;
import com.nhnacademy.edu.certificateissueservice.repository.FamilyRelationshipRepositoryCustom;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FamilyRelationshipRepositoryImpl extends QuerydslRepositorySupport implements FamilyRelationshipRepositoryCustom {
    public FamilyRelationshipRepositoryImpl() {
        super(FamilyRelationship.class);
    }

    @Override
    public List<FamilyRelationshipDto> getFamilyRelationships(Integer serialNumber) {
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;
        QResident resident = QResident.resident;
        return from(familyRelationship)
                .innerJoin(resident)
                .on(familyRelationship.pk.familyResidentSerialNumber.eq(resident.residentSerialNumber))
                .where(familyRelationship.pk.baseResidentSerialNumber.eq(serialNumber))
                .select(Projections.constructor(FamilyRelationshipDto.class,
                        familyRelationship.familyRelationshipCode,
                        resident.name,
                        resident.birthDate,
                        resident.residentRegistrationNumber,
                        resident.genderCode))
                .fetch();

    }
}
