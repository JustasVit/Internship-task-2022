package com.visma.warehouse.services;

import com.visma.warehouse.dto.ProductDto;
import com.visma.warehouse.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product buyProduct(long id, int quantity);

}
