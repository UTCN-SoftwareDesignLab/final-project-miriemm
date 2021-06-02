package com.finalproject.report;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReportServiceFactoryTest {

    @Autowired
    private ReportServiceFactory reportServiceFactory;

    @Test
    void getReportService() {
//        ReportService pdfReportService = reportServiceFactory.getReportService(PDF);
//        Assertions.assertEquals("I am a PDF reporter.", pdfReportService.export());
    }
}