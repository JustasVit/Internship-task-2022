package com.visma.shop.services;

import com.visma.shop.dto.ProductDto;

import java.util.List;

public interface ShopService {

    List<ProductDto> getAllProducts();
    ProductDto buyProduct(long id, int quantity);
}
