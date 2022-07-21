package com.visma.warehouse.services;

import java.io.IOException;
import java.time.LocalDateTime;

public interface ReportService {

    void generateReport(LocalDateTime startDate, LocalDateTime endDate) throws IOException;
}
