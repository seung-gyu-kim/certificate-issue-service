package com.nhnacademy.edu.certificateissueservice.controller;

import com.nhnacademy.edu.certificateissueservice.service.CertificateIssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class CertificateIssueController {
    private final CertificateIssueService certificateIssueService;
    @GetMapping("/{serialNumber}/certificate-issues")
    public ModelAndView viewCertificateIssueList(@PathVariable Integer serialNumber, Pageable pageable) {
        ModelAndView mav = new ModelAndView("certificate_issue_list");
        mav.addObject("certificateIssuePage", certificateIssueService.getCertificateIssueList(serialNumber, pageable));
        return mav;
    }

    @GetMapping("/{serialNumber}/resident-registeration-certificate")
    public ModelAndView viewResidentRegistrationCertificate(@PathVariable Integer serialNumber, Pageable pageable) {
        ModelAndView mav = new ModelAndView("resident_registeration_certificate");
        return mav;
    }
}
