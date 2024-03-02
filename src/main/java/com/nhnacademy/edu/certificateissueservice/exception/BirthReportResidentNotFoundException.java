package com.nhnacademy.edu.certificateissueservice.exception;

import com.nhnacademy.edu.certificateissueservice.entity.BirthDeathReportResident;

public class BirthReportResidentNotFoundException extends RuntimeException {
    public BirthReportResidentNotFoundException(BirthDeathReportResident.PK pk) {
        super(String.format("출생신고서가 없습니다.(%s)", pk));
    }
}
