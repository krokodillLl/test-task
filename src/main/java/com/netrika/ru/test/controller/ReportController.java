package com.netrika.ru.test.controller;

import com.itextpdf.text.DocumentException;
import com.netrika.ru.test.services.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final Logger logger = LoggerFactory.getLogger(ReportController.class);

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(method = RequestMethod.GET)
    StreamingResponseBody getPDF(HttpServletResponse response){
        try {
            return reportService.getPDF(response);
        } catch (IOException | DocumentException e) {
            logger.error("The report file cannot be created");
            return null;
        }
    }
}
