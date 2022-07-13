package com.visma.warehouse.services;

import com.visma.warehouse.dto.ProductDto;
import java.util.List;
public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto buyProduct(long id, int quantity);

}
