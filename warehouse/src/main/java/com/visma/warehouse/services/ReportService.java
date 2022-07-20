package com.visma.warehouse.services;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ReportService {

    void generateScheduledReport() throws IOException;
}
