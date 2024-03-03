package com.nhnacademy.edu.certificateissueservice.repository;

import com.nhnacademy.edu.certificateissueservice.dto.FamilyRelationshipDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface FamilyRelationshipRepositoryCustom {
    List<FamilyRelationshipDto> getFamilyRelationships(Integer serialNumber);
}
