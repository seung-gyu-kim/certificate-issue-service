package com.nhnacademy.edu.certificateissueservice.dto.response;

import com.nhnacademy.edu.certificateissueservice.dto.FamilyRelationshipDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FamilyRelationshipCertificateResponseDto {
    private LocalDate certificateIssueDate;
    private Long certificateConfirmationNumber;
    private String registrationBaseAddress;
    private List<FamilyRelationshipDto> familyRelationshipList;

    public String getCertificateConfirmationNumber() {
        return certificateConfirmationNumber.toString().substring(0, 8) + "-" + certificateConfirmationNumber.toString().substring(8);
    }
}
