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

// insert into birth_death_report_resident values (7, '출생', 4, '20120317', '부', null, 'nam@nhnad.co.kr', '010-1234-5678');
// insert into birth_death_report_resident values (1, '사망', 2, '20200502', '비동거친족', null, null, '010-2345-6789');
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class BirthDeathReportResidentTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void test() {
        BirthDeathReportResident birthDeathReportResident = entityManager.find(BirthDeathReportResident.class, new BirthDeathReportResident.PK(7, "출생", 4));

        assertThat(birthDeathReportResident.getPhoneNumber()).isEqualTo("010-1234-5678");
    }
}