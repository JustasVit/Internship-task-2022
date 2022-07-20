package com.visma.warehouse.services;

import com.visma.warehouse.models.ShopProduct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface FileService {

    File readFile(LocalDateTime dateTime) throws FileNotFoundException;

    void createFile(String filepath, List<ShopProduct> content) throws IOException;
}
