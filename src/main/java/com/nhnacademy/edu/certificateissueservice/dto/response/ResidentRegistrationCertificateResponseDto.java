package com.nhnacademy.edu.certificateissueservice.dto.response;

import com.nhnacademy.edu.certificateissueservice.dto.HouseholdCompositionResidentDto;
import com.nhnacademy.edu.certificateissueservice.dto.HouseholdMovementAddressDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResidentRegistrationCertificateResponseDto {
    private LocalDate certificateIssueDate;
    private Long certificateConfirmationNumber;
    private String name;
    private String householdCompositionChangeReasonCode;
    private LocalDate reportDate;
    private List<HouseholdMovementAddressDto> householdMovementAddressList;
    private List<HouseholdCompositionResidentDto> householdCompositionResidentList;

    public String getCertificateConfirmationNumber() {
        return certificateConfirmationNumber.toString().substring(0, 8) + "-" + certificateConfirmationNumber.toString().substring(8);
    }
}
