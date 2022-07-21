package com.visma.warehouse.services;

import com.visma.warehouse.models.ShopProduct;
import com.visma.warehouse.repositories.ShopProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Profile("database")
@AllArgsConstructor
public class CsvReportServiceImpl implements ReportService {

    private ShopProductRepository shopProductRepository;

    private FileService fileService;

    public void generateScheduledReport(LocalDateTime startDate, LocalDateTime endDate) throws IOException {

        if(startDate.isAfter(endDate)){
            throw new IllegalArgumentException("Starting date is after ending date!");
        }

        List<ShopProduct> shoppingHistory = shopProductRepository
                .findAllByDateBetween(
                        startDate.truncatedTo(ChronoUnit.HOURS),
                        endDate.truncatedTo(ChronoUnit.HOURS));

        fileService.createFile(shoppingHistory);
    }
}
