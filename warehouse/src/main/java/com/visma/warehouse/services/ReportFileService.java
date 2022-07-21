package com.visma.warehouse.services;

import com.visma.warehouse.models.ShopProduct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface ReportFileService {

    File readFile(LocalDateTime dateTime) throws FileNotFoundException;

    void createFile(List<ShopProduct> content) throws IOException;
}
