package com.visma.shop.services;

import com.visma.shop.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final RestTemplate restTemplate;

    @Value("${warehouse.baseUrl}")
    private String warehouseBaseUrl;

    public WarehouseServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .basicAuthentication("user", "password")
                .build();
    }

    public List<ProductDto> getAllProducts() {
        return Arrays.asList(
                restTemplate.getForObject(
                        String.format("%s/api/products", warehouseBaseUrl),
                        ProductDto[].class));
    }

    public ProductDto buyProduct(long id, int quantity) {
        return restTemplate.postForObject(
                String.format("%s/api/products/product/%d/retrieve/%d", warehouseBaseUrl,id,quantity),
                null,
                ProductDto.class);
    }
}
