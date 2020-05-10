package com.netrika.ru.test.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.netrika.ru.test.dto.EmployeeTO;
import com.netrika.ru.test.utils.EmployeeTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ReportService {

    private final Logger logger = LoggerFactory.getLogger(ReportService.class);
    private final EmployeeService employeeService;
    public ReportService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public StreamingResponseBody getPDF(HttpServletResponse response) throws IOException, DocumentException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(EmployeeTO.class, new EmployeeTOConverter());
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(employeeService.getAllEmployees());
        String[] strings = json.split("\n");
        Document pdfDoc = new Document(PageSize.A4);
        PdfWriter.getInstance(pdfDoc, new FileOutputStream("Report.pdf"))
                .setPdfVersion(PdfWriter.PDF_VERSION_1_7);
        pdfDoc.open();
        BaseFont font = null;
            font = BaseFont.createFont("fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
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

        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"Report.pdf\"");
        InputStream inputStream = new FileInputStream(new File("Report.pdf"));

        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
            inputStream.close();
            if(new File("Report.pdf").delete()) {
                logger.info("The temp file \"Report.pdf\" was deleted");
            }
            else {
                logger.info("The temp file wasn't deleted");
            }
        };
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

    private void writePDF(String string, Font myFont, Document pdfDoc) throws DocumentException {
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
                    pdfDoc.add(para);
            }
        }
    }
}
