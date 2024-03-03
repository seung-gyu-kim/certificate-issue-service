package com.nhnacademy.edu.certificateissueservice.service.impl;

import com.nhnacademy.edu.certificateissueservice.dto.BirthReportRequestDto;
import com.nhnacademy.edu.certificateissueservice.entity.*;
import com.nhnacademy.edu.certificateissueservice.exception.HouseholdNotFoundException;
import com.nhnacademy.edu.certificateissueservice.exception.ResidentNotFoundException;
import com.nhnacademy.edu.certificateissueservice.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.edu.certificateissueservice.repository.FamilyRelationshipRepository;
import com.nhnacademy.edu.certificateissueservice.repository.HouseholdCompositionResidentRepository;
import com.nhnacademy.edu.certificateissueservice.repository.ResidentRepository;
import com.nhnacademy.edu.certificateissueservice.service.BirthReportResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BirthReportResidentServiceImpl implements BirthReportResidentService {
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final ResidentRepository residentRepository;
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;

    @Override
    @Transactional
    public BirthDeathReportResident registerBirthReportResident(Integer reportSerialNumber, BirthReportRequestDto requestDto) {
        // 출생 주민 등록
        Resident targetResident = new Resident();
        targetResident.setResidentSerialNumber(ResidentServiceImpl.sequence.incrementAndGet());
        targetResident.setName(requestDto.getTargetResident().getName());
        targetResident.setResidentRegistrationNumber(getResidentRegistrationNumber(requestDto.getTargetResident().getBirthDate()));
        targetResident.setGenderCode(requestDto.getTargetResident().getGenderCode());
        targetResident.setBirthDate(requestDto.getTargetResident().getBirthDate());
        targetResident.setBirthPlaceCode(requestDto.getTargetResident().getBirthPlaceCode());
        targetResident.setRegistrationBaseAddress(requestDto.getTargetResident().getRegistrationBaseAddress());
        targetResident = residentRepository.save(targetResident);

        // 출생 신고 주민 등록
        Resident reportResident = residentRepository.findById(reportSerialNumber).orElseThrow(() -> new ResidentNotFoundException());

        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        BirthDeathReportResident.PK pk = new BirthDeathReportResident.PK(
                targetResident.getResidentSerialNumber(), "출생", reportResident.getResidentSerialNumber()
        );
        birthDeathReportResident.setPk(pk);
        birthDeathReportResident.setBirthDeathReportDate(requestDto.getBirthReportDate());
        birthDeathReportResident.setBirthReportQualificationsCode(requestDto.getReportResident().getBirthReportQualificationsCode());
        birthDeathReportResident.setEmailAddress(requestDto.getReportResident().getEmailAddress());
        birthDeathReportResident.setPhoneNumber(requestDto.getReportResident().getPhoneNumber());
        birthDeathReportResident.setResident(targetResident);
        BirthDeathReportResident savedBirthDeathReportResident = birthDeathReportResidentRepository.save(birthDeathReportResident);

        // 가족 관계 자동 등록
        List<FamilyRelationship> pendingFamilyRelationships = new ArrayList<>();


        Optional<Resident> fatherResidentOptional = residentRepository.findByNameAndResidentRegistrationNumber(
                requestDto.getFatherResident().getName(), requestDto.getFatherResident().getResidentRegistrationNumber());
        if(fatherResidentOptional.isPresent()) {
            FamilyRelationship familyRelationshipBaseFather = new FamilyRelationship();
            familyRelationshipBaseFather.setPk(new FamilyRelationship.PK(targetResident.getResidentSerialNumber(), fatherResidentOptional.get().getResidentSerialNumber()));
            familyRelationshipBaseFather.setFamilyRelationshipCode("부");
            familyRelationshipBaseFather.setBaseResident(targetResident);
            pendingFamilyRelationships.add(familyRelationshipBaseFather);

            FamilyRelationship familyRelationshipFatherBase = new FamilyRelationship();
            familyRelationshipFatherBase.setPk(new FamilyRelationship.PK(fatherResidentOptional.get().getResidentSerialNumber(), targetResident.getResidentSerialNumber()));
            familyRelationshipFatherBase.setFamilyRelationshipCode("자녀");
            familyRelationshipFatherBase.setBaseResident(fatherResidentOptional.get());
            pendingFamilyRelationships.add(familyRelationshipFatherBase);
        }


        Optional<Resident> motherResidentOptional = residentRepository.findByNameAndResidentRegistrationNumber(
                requestDto.getMotherResident().getName(), requestDto.getMotherResident().getResidentRegistrationNumber());
        if(motherResidentOptional.isPresent()) {
            FamilyRelationship familyRelationshipBaseMother = new FamilyRelationship();
            familyRelationshipBaseMother.setPk(new FamilyRelationship.PK(targetResident.getResidentSerialNumber(), motherResidentOptional.get().getResidentSerialNumber()));
            familyRelationshipBaseMother.setFamilyRelationshipCode("모");
            familyRelationshipBaseMother.setBaseResident(targetResident);
            pendingFamilyRelationships.add(familyRelationshipBaseMother);

            FamilyRelationship familyRelationshipMotherBase = new FamilyRelationship();
            familyRelationshipMotherBase.setPk(new FamilyRelationship.PK(motherResidentOptional.get().getResidentSerialNumber(), targetResident.getResidentSerialNumber()));
            familyRelationshipMotherBase.setFamilyRelationshipCode("자녀");
            familyRelationshipMotherBase.setBaseResident(motherResidentOptional.get());
            pendingFamilyRelationships.add(familyRelationshipMotherBase);
        }

        familyRelationshipRepository.saveAll(pendingFamilyRelationships);

        // 세대 구성원 자동 등록
        Resident parentResident;
        if(fatherResidentOptional.isPresent()) parentResident = fatherResidentOptional.get();
        else if(motherResidentOptional.isPresent()) parentResident = motherResidentOptional.get();
        else return savedBirthDeathReportResident;

        Household household = householdCompositionResidentRepository.findByResident_ResidentSerialNumber(parentResident.getResidentSerialNumber())
                .orElseThrow(() -> new HouseholdNotFoundException()).getHousehold();

        HouseholdCompositionResident householdCompositionResident = new HouseholdCompositionResident();
        householdCompositionResident.setPk(new HouseholdCompositionResident.PK(household.getHouseholdSerialNumber(), targetResident.getResidentSerialNumber()));
        householdCompositionResident.setReportDate(requestDto.getBirthReportDate());
        householdCompositionResident.setHouseholdRelationshipCode("자녀");
        householdCompositionResident.setHouseholdCompositionChangeReasonCode("출생등록");
        householdCompositionResident.setHousehold(household);
        householdCompositionResident.setResident(targetResident);
        householdCompositionResidentRepository.save(householdCompositionResident);

        return savedBirthDeathReportResident;
    }

    public String getResidentRegistrationNumber(LocalDateTime birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String front = birthDate.format(formatter);
        StringBuilder residentRegistrationNumber = new StringBuilder(front);
        residentRegistrationNumber.append("-12345");
        residentRegistrationNumber.append((int)(Math.random() * 9 + 1));
        residentRegistrationNumber.append((int)(Math.random() * 9 + 1));
        return residentRegistrationNumber.toString();
    }

    @Override
    @Transactional
    public BirthDeathReportResident modifyBirthReportResident(Integer reportSerialNumber, Integer targetSerialNumber, BirthReportRequestDto requestDto) {
        Resident targetResident = residentRepository.findById(targetSerialNumber).orElseThrow(() -> new ResidentNotFoundException(targetSerialNumber));
        targetResident.setName(requestDto.getTargetResident().getName());
        targetResident.setResidentRegistrationNumber(getResidentRegistrationNumber(requestDto.getTargetResident().getBirthDate()));
        targetResident.setGenderCode(requestDto.getTargetResident().getGenderCode());
        targetResident.setBirthDate(requestDto.getTargetResident().getBirthDate());
        targetResident.setBirthPlaceCode(requestDto.getTargetResident().getBirthPlaceCode());
        targetResident.setRegistrationBaseAddress(requestDto.getTargetResident().getRegistrationBaseAddress());

        // 기존 관계들 삭제
        birthDeathReportResidentRepository.deleteById(new BirthDeathReportResident.PK(targetSerialNumber, "출생", reportSerialNumber));
        familyRelationshipRepository.deleteAllByPk_BaseResidentSerialNumber(targetSerialNumber);
        householdCompositionResidentRepository.deleteAllByPk_ResidentSerialNumber(targetSerialNumber);

        // 출생 신고 주민 등록
        Resident reportResident = residentRepository.findById(reportSerialNumber).orElseThrow(() -> new ResidentNotFoundException());

        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();
        BirthDeathReportResident.PK pk = new BirthDeathReportResident.PK(
                targetResident.getResidentSerialNumber(), "출생", reportResident.getResidentSerialNumber()
        );
        birthDeathReportResident.setPk(pk);
        birthDeathReportResident.setBirthDeathReportDate(requestDto.getBirthReportDate());
        birthDeathReportResident.setBirthReportQualificationsCode(requestDto.getReportResident().getBirthReportQualificationsCode());
        birthDeathReportResident.setEmailAddress(requestDto.getReportResident().getEmailAddress());
        birthDeathReportResident.setPhoneNumber(requestDto.getReportResident().getPhoneNumber());
        birthDeathReportResident.setResident(targetResident);
        BirthDeathReportResident savedBirthDeathReportResident = birthDeathReportResidentRepository.save(birthDeathReportResident);

        // 가족 관계 자동 등록
        List<FamilyRelationship> pendingFamilyRelationships = new ArrayList<>();


        Optional<Resident> fatherResidentOptional = residentRepository.findByNameAndResidentRegistrationNumber(
                requestDto.getFatherResident().getName(), requestDto.getFatherResident().getResidentRegistrationNumber());
        if(fatherResidentOptional.isPresent()) {
            FamilyRelationship familyRelationshipBaseFather = new FamilyRelationship();
            familyRelationshipBaseFather.setPk(new FamilyRelationship.PK(targetResident.getResidentSerialNumber(), fatherResidentOptional.get().getResidentSerialNumber()));
            familyRelationshipBaseFather.setFamilyRelationshipCode("부");
            familyRelationshipBaseFather.setBaseResident(targetResident);
            pendingFamilyRelationships.add(familyRelationshipBaseFather);

            FamilyRelationship familyRelationshipFatherBase = new FamilyRelationship();
            familyRelationshipFatherBase.setPk(new FamilyRelationship.PK(fatherResidentOptional.get().getResidentSerialNumber(), targetResident.getResidentSerialNumber()));
            familyRelationshipFatherBase.setFamilyRelationshipCode("자녀");
            familyRelationshipFatherBase.setBaseResident(fatherResidentOptional.get());
            pendingFamilyRelationships.add(familyRelationshipFatherBase);
        }


        Optional<Resident> motherResidentOptional = residentRepository.findByNameAndResidentRegistrationNumber(
                requestDto.getMotherResident().getName(), requestDto.getMotherResident().getResidentRegistrationNumber());
        if(motherResidentOptional.isPresent()) {
            FamilyRelationship familyRelationshipBaseMother = new FamilyRelationship();
            familyRelationshipBaseMother.setPk(new FamilyRelationship.PK(targetResident.getResidentSerialNumber(), motherResidentOptional.get().getResidentSerialNumber()));
            familyRelationshipBaseMother.setFamilyRelationshipCode("모");
            familyRelationshipBaseMother.setBaseResident(targetResident);
            pendingFamilyRelationships.add(familyRelationshipBaseMother);

            FamilyRelationship familyRelationshipMotherBase = new FamilyRelationship();
            familyRelationshipMotherBase.setPk(new FamilyRelationship.PK(motherResidentOptional.get().getResidentSerialNumber(), targetResident.getResidentSerialNumber()));
            familyRelationshipMotherBase.setFamilyRelationshipCode("자녀");
            familyRelationshipMotherBase.setBaseResident(motherResidentOptional.get());
            pendingFamilyRelationships.add(familyRelationshipMotherBase);
        }

        familyRelationshipRepository.saveAll(pendingFamilyRelationships);

        // 세대 구성원 자동 등록
        Resident parentResident;
        if(fatherResidentOptional.isPresent()) parentResident = fatherResidentOptional.get();
        else if(motherResidentOptional.isPresent()) parentResident = motherResidentOptional.get();
        else return savedBirthDeathReportResident;

        Household household = householdCompositionResidentRepository.findByResident_ResidentSerialNumber(parentResident.getResidentSerialNumber())
                .orElseThrow(() -> new HouseholdNotFoundException()).getHousehold();

        HouseholdCompositionResident householdCompositionResident = new HouseholdCompositionResident();
        householdCompositionResident.setPk(new HouseholdCompositionResident.PK(household.getHouseholdSerialNumber(), targetResident.getResidentSerialNumber()));
        householdCompositionResident.setReportDate(requestDto.getBirthReportDate());
        householdCompositionResident.setHouseholdRelationshipCode("자녀");
        householdCompositionResident.setHouseholdCompositionChangeReasonCode("출생등록");
        householdCompositionResident.setHousehold(household);
        householdCompositionResident.setResident(targetResident);
        householdCompositionResidentRepository.save(householdCompositionResident);

        return savedBirthDeathReportResident;
    }

    @Override
    @Transactional
    public void removeBirthReportResident(Integer reportSerialNumber, Integer targetSerialNumber) {
        residentRepository.deleteById(targetSerialNumber);
        birthDeathReportResidentRepository.deleteById(new BirthDeathReportResident.PK(targetSerialNumber, "출생", reportSerialNumber));
        familyRelationshipRepository.deleteAllByPk_BaseResidentSerialNumber(targetSerialNumber);
        householdCompositionResidentRepository.deleteAllByPk_ResidentSerialNumber(targetSerialNumber);
    }
}
