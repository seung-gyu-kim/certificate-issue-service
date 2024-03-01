package com.nhnacademy.edu.certificateissueservice.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.nhnacademy.edu.certificateissueservice.dto.ResidentRegisterRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.Resident;
import com.nhnacademy.edu.certificateissueservice.service.ResidentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
class ResidentRestControllerTest {
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;
    private AutoCloseable closeable;
    @InjectMocks
    private ResidentRestController residentRestController;
    @Mock
    private ResidentService residentService;

    @BeforeEach
    void setUp() {
        objectMapper = objectMapper();
        closeable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(residentRestController)
                .setMessageConverters( new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

//    insert into resident values(1, '남길동', '130914-1234561', '남', '19130914072200', '자택', '경기도 성남시 분당구 대왕판교로645번길', '20210429090300', '주택', '강원도 고성군 금강산로 290번길');
    @Test
    void postResident() throws Exception {
        // given
        Resident resident = new Resident();
        resident.setResidentSerialNumber(10);
        resident.setName("남길동");
        resident.setResidentRegistrationNumber("130914-1234561");
        resident.setGenderCode("남");
        resident.setBirthDate(LocalDateTime.parse("19130914072200", DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        resident.setBirthPlaceCode("자택");
        resident.setRegistrationBaseAddress("경기도 성남시 분당구 대왕판교로645번길");
        resident.setDeathDate(LocalDateTime.parse("20210429090300", DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        resident.setDeathPlaceCode("주택");
        resident.setDeathPlaceAddress("강원도 고성군 금강산로 290번길");

        when(residentService.registerResident(any(ResidentRegisterRequestDto.class))).thenReturn(resident);

        String request = "{\n" +
                "  \"residentSerialNumber\" : 10,\n" +
                "  \"name\" : \"남길동\",\n" +
                "  \"residentRegistrationNumber\" : \"130914-1234561\",\n" +
                "  \"genderCode\" : \"남\",\n" +
                "  \"birthDate\" : \"19130914072200\",\n" +
                "  \"birthPlaceCode\" : \"자택\",\n" +
                "  \"registrationBaseAddress\" : \"경기도 성남시 분당구 대왕판교로645번길\",\n" +
                "  \"deathDate\" : \"20210429090300\",\n" +
                "  \"deathPlaceCode\" : \"주택\",\n" +
                "  \"deathPlaceAddress\" : \"강원도 고성군 금강산로 290번길\"\n" +
                "}";

        // when & then
        mockMvc.perform(post("/residents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andDo(log())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/residents/10"))
                .andExpectAll(
                        jsonPath("$.residentSerialNumber").value(10),
                        jsonPath("$.name").value("남길동"),
                        jsonPath("$.residentRegistrationNumber").value("130914-1234561"),
                        jsonPath("$.genderCode").value("남"),
                        jsonPath("$.birthDate").value("1913-09-14T07:22:00"),
                        jsonPath("$.birthPlaceCode").value("자택"),
                        jsonPath("$.registrationBaseAddress").value("경기도 성남시 분당구 대왕판교로645번길"),
                        jsonPath("$.deathDate").value("2021-04-29T09:03:00"),
                        jsonPath("$.deathPlaceCode").value("주택"),
                        jsonPath("$.deathPlaceAddress").value("강원도 고성군 금강산로 290번길"));
    }

    @Test
    void putResident() {
        // given

        // when

        // then
    }

    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //pretty print json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //value -> null 무시
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //LocalDate, LocalDateTime support jsr310
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        objectMapper.registerModule(javaTimeModule);
        //timestamp 출력을 disable. -> 문자열형태로 출력
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}