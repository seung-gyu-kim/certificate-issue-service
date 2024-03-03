package com.nhnacademy.edu.certificateissueservice.dto;

import java.time.LocalDate;

public interface HouseholdMovementAddressDto {
    String getHouseMovementAddress();
    String getLastAddressYn();
    LocalDate getHouseMovementReportDate();
}
