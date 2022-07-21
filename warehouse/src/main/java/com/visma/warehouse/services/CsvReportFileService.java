package com.visma.warehouse.services;

import com.visma.warehouse.models.ShopProduct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Profile("database")
public class CsvReportFileService implements ReportFileService {

    @Value("${report.file.path}")
    String path;

    public void createFile(List<ShopProduct> shopProducts) throws IOException {

        String filename = generateFilename(LocalDateTime.now());
        FileWriter fileWriter = new FileWriter(String.format("%s/%s",path,filename));

        try (CSVPrinter printer = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {
            for(ShopProduct shopProduct : shopProducts){
                printer.printRecord(
                        shopProduct.getShop().getUsername(),
                        shopProduct.getProduct().getName(),
                        shopProduct.getQuantity(),
                        shopProduct.getDate());
            }
        }
    }

    public File readFile(LocalDateTime dateTime) throws FileNotFoundException{

        String filename = generateFilename(dateTime);
        Path filepath = Paths.get(String.format("%s/%s",path,filename));

        if(!Files.exists(filepath)){
            throw new FileNotFoundException("Report file was not found!");
        }

        return filepath.toFile();
    }

    public String generateFilename(LocalDateTime dateTime){

        return dateTime.truncatedTo(ChronoUnit.HOURS)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH'.csv'"));
    }

}
