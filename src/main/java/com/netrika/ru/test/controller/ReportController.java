package com.netrika.ru.test.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.netrika.ru.test.services.ReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Document getPDF() {
        try {
             reportService.getPDF();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
