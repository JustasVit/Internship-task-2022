package com.visma.shop.services;

import com.visma.warehousedto.dto.ProductDto;
import org.springframework.core.io.Resource;
import java.time.LocalDateTime;
import java.util.List;

public interface WarehouseService {

    List<ProductDto> getAllProducts();

    ProductDto buyProduct(long id, int quantity);

    Resource getReport(LocalDateTime date);
}
