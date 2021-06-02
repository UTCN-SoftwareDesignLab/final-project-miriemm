package com.finalproject.report;

public interface ReportService {
    byte[] export(Long bookingID);

    ReportType getType();
}
