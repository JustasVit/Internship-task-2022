package com.visma.warehouse.services;

import com.visma.warehouse.models.ShopProduct;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {

    void createFile(String filepath, List<ShopProduct> content) throws IOException;
}
