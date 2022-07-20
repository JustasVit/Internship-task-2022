package com.visma.warehouse.services;

import com.visma.warehouse.models.ShopProduct;
import com.visma.warehouse.repositories.ShopProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Profile("database")
public class CsvReportServiceImpl implements ReportService {

    @Value("${file.csv.path}")
    private String filepath;

    private ShopProductRepository shopProductRepository;

    private FileService fileService;

    public CsvReportServiceImpl(ShopProductRepository shopProductRepository, FileService fileService){
        this.shopProductRepository = shopProductRepository;
        this.fileService = fileService;
    }

    @Scheduled(cron = "0 0 0-23 * * *")
    public void generateScheduledReport() throws IOException {
        List<ShopProduct> shoppingHistory = shopProductRepository
                .findAllByDateBetween(
                        LocalDateTime.now().minusHours(1).truncatedTo(ChronoUnit.HOURS),
                        LocalDateTime.now().truncatedTo(ChronoUnit.HOURS));

        fileService.createFile(filepath, shoppingHistory);
    }
}
