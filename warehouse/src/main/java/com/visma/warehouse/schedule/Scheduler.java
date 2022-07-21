package com.visma.warehouse.schedule;

import com.visma.warehouse.services.ReportService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@EnableScheduling
@Profile("database")
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
class Scheduler {

    private ReportService reportService;

    @Value("${report.interval}")
    private Integer interval;

    public Scheduler(ReportService reportService){
        this.reportService = reportService;
    }

    @Scheduled(cron = "${report.generation.frequency}" )
    private void generateReport() throws IOException {
        reportService.generateReport(LocalDateTime.now().minusHours(interval), LocalDateTime.now());
    }
}