package com.visma.warehouse.services;

import com.visma.warehouse.models.ShopProduct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Profile("database")
public class CsvFileService implements FileService{

    public void createFile(String filepath, List<ShopProduct> shopProducts) throws IOException {
        String filename = generateFilename();
        FileWriter fileWriter = new FileWriter(String.format("%s%s",filepath,filename));
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

    private String generateFilename(){
        return String.format(
                "%s.csv",
                LocalDateTime
                        .now()
                        .truncatedTo(ChronoUnit.MINUTES)
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH")));
    }

}
