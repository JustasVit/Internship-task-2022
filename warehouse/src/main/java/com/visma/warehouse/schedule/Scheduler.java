package com.visma.warehouse.schedule;

import com.visma.warehouse.services.ReportService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
class Scheduler {

    private ReportService reportService;

    @Value("${report.interval}")
    private Integer interval;

    public Scheduler(ReportService reportService){
        this.reportService = reportService;
    }

    @Scheduled(cron = "0 0 0-23 * * *")
    private void generateReport() throws IOException {
        reportService.generateScheduledReport(LocalDateTime.now().minusHours(interval), LocalDateTime.now());
    }
}