package com.nhnacademy.edu.certificateissueservice.controller;

import com.nhnacademy.edu.certificateissueservice.service.IndexQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final IndexQueryService indexQueryService;

    @GetMapping("/")
    public ModelAndView index(Pageable pageable) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("residentReportPage", indexQueryService.getResidentReport(pageable));
        return mav;
    }
}
