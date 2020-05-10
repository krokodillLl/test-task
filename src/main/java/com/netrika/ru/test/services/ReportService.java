package com.netrika.ru.test.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.netrika.ru.test.dto.EmployeeTO;
import com.netrika.ru.test.utils.EmployeeTOConverter;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ReportService {

    private final EmployeeService employeeService;
    public ReportService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Document getPDF() throws FileNotFoundException, DocumentException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(EmployeeTO.class, new EmployeeTOConverter());
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(employeeService.getAllEmployees());
        String[] strings = json.split("\n");
        Document pdfDoc = new Document(PageSize.A4);
        PdfWriter.getInstance(pdfDoc, new FileOutputStream("myJSON.pdf"))
                .setPdfVersion(PdfWriter.PDF_VERSION_1_7);
        pdfDoc.open();
        BaseFont font = null;
        try {
            font = BaseFont.createFont("c:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Font myFont = new Font(font);
        myFont.setStyle(Font.NORMAL);
        myFont.setSize(11);
        pdfDoc.add(new Paragraph("\n"));
        for(String s: strings) {
            if(isUglyText(s)) {
                String[] str = s.split("\\\\n");
                for(String st: str) {
                    writePDF(st, myFont, pdfDoc);
                }
            }
            else {
                writePDF(s, myFont, pdfDoc);
            }
        }
        pdfDoc.close();
        return pdfDoc;
    }

    private Boolean isUglyText(String uglyText) {
        return (uglyText.split("\\\\n").length > 1);
    }

    private ArrayList<String> getPrettyText(String text) {
        text = text.replaceAll("\\\\", "");
        text = text.replaceAll(",", "");
        text = text.replaceAll("\"", "");
        text = text.replaceAll("\"", "");
        text = text.replaceAll("\\[", "");
        text = text.replaceAll("]", "");
        text = text.replaceAll("\\{", "");
        text = text.replaceAll("}", "\n");
        String[] texts = text.split("\n");
        return new ArrayList<>(Arrays.asList(texts));
    }

    private void writePDF(String string, Font myFont, Document pdfDoc) {
        if(!string.equals("")) {
            ArrayList<String> list = getPrettyText(string);
            for(String current: list) {
                if(current.contains("ФИО")) {
                    myFont.setStyle(Font.BOLD);
                }
                else {
                    myFont.setStyle(Font.NORMAL);
                }
                Paragraph para = new Paragraph(current + "\n", myFont);
                para.setAlignment(Element.ALIGN_JUSTIFIED);
                try {
                    pdfDoc.add(para);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
