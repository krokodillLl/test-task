package com.netrika.ru.test.controller;

import com.itextpdf.text.DocumentException;
import com.netrika.ru.test.services.ReportService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/report")
public class ReportController {


    private final ReportService reportService;

    public ReportController(@Qualifier("reportService")ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping()
    StreamingResponseBody getPDF(HttpServletResponse response){
        try {
            return reportService.getPDF(response);
        } catch (IOException | DocumentException e) {
            return null;
        }
    }
}
