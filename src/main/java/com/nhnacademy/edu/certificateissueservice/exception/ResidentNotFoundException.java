package com.nhnacademy.edu.certificateissueservice.exception;

public class ResidentNotFoundException extends RuntimeException {
    public ResidentNotFoundException(Integer residentSerialNumber) {
        super(String.format("주민 정보를 찾을 수 없습니다. (주민일련번호: %d)", residentSerialNumber));
    }
}
