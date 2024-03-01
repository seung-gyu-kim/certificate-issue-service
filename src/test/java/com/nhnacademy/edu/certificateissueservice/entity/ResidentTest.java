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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

/*
insert into resident values(1, '남길동', '130914-1234561', '남', '19130914072200', '자택', '경기도 성남시 분당구 대왕판교로645번길', '20210429090300', '주택', '강원도 고성군 금강산로 290번길');
insert into resident values(2, '남석환', '540514-1234562', '남', '19540514173000', '병원', '경기도 성남시 분당구 대왕판교로645번길', null, null, null);
insert into resident values(3, '박한나', '551022-1234563', '여', '19551022111500', '병원', '서울특별시 중구 세종대로 110번길', null, null, null);
insert into resident values(4, '남기준', '790510-1234564', '남', '19790510204500', '병원', '경기도 성남시 분당구 대왕판교로645번길', null, null, null);
insert into resident values(5, '이주은', '820821-1234565', '여', '19820821012800', '병원', '경기도 수원시 팔달구 효원로 1번길', null, null, null);
insert into resident values(6, '이선미', '851205-1234566', '여', '19851205220100', '병원', '경기도 수원시 팔달구 효원로 1번길', null, null, null);
insert into resident values(7, '남기석', '120315-1234567', '남', '20120315145900', '병원', '경기도 성남시 분당구 대왕판교로645번길', null, null, null);
 */
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class ResidentTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void test() {
        Resident resident = entityManager.find(Resident.class, 1);

        assertSoftly(softly -> {
            softly.assertThat(resident.getResidentSerialNumber()).isEqualTo(1);
            softly.assertThat(resident.getName()).isEqualTo("남길동");
            softly.assertThat(resident.getResidentRegistrationNumber()).isEqualTo("130914-1234561");
            softly.assertThat(resident.getGenderCode()).isEqualTo("남");
            softly.assertThat(resident.getBirthDate()).isEqualTo(LocalDateTime.parse("19130914072200", DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            softly.assertThat(resident.getBirthPlaceCode()).isEqualTo("자택");
            softly.assertThat(resident.getRegistrationBaseAddress()).isEqualTo("경기도 성남시 분당구 대왕판교로645번길");
            softly.assertThat(resident.getDeathDate()).isEqualTo(LocalDateTime.parse("20210429090300", DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            softly.assertThat(resident.getDeathPlaceCode()).isEqualTo("주택");
            softly.assertThat(resident.getDeathPlaceAddress()).isEqualTo("강원도 고성군 금강산로 290번길");
        });
    }
}