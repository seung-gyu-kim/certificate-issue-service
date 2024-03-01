package com.nhnacademy.edu.certificateissueservice.entity;

import com.nhnacademy.edu.certificateissueservice.config.RootConfig;
import com.nhnacademy.edu.certificateissueservice.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//insert into family_relationship values(1, 2, '자녀');
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class FamilyRelationshipTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void test() {
        FamilyRelationship familyRelationship = entityManager.find(FamilyRelationship.class, new FamilyRelationship.PK(1, 2));

        assertThat(familyRelationship.getFamilyRelationshipCode()).isEqualTo("자녀");
    }
}