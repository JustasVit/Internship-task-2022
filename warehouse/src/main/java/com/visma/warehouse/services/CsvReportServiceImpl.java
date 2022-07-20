package com.visma.warehouse.services;

import com.visma.warehouse.models.ShopProduct;
import com.visma.warehouse.repositories.ShopProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Profile("database")
@AllArgsConstructor
public class CsvReportServiceImpl implements ReportService {

    private ShopProductRepository shopProductRepository;

    private FileService fileService;

    @Scheduled(cron = "0 0 0-23 * * *")
    public void generateScheduledReport() throws IOException {
        List<ShopProduct> shoppingHistory = shopProductRepository
                .findAllByDateBetween(
                        LocalDateTime.now().minusHours(1).truncatedTo(ChronoUnit.HOURS),
                        LocalDateTime.now().truncatedTo(ChronoUnit.HOURS));

        fileService.createFile(shoppingHistory);
    }
}
