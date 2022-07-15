package com.visma.shop.services;

import com.visma.warehousedto.dto.ProductDto;

import java.util.List;

public interface WarehouseService {

    List<ProductDto> getAllProducts();
    ProductDto buyProduct(long id, int quantity);
}
