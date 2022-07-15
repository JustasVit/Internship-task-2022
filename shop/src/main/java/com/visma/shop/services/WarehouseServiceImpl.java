package com.visma.shop.services;

import com.visma.warehousedto.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostConstruct
    public void init(){
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(warehouseBaseUrl));
    }

    public List<ProductDto> getAllProducts() {
        return Arrays.asList(
                restTemplate.getForObject(
                        "/api/products",
                        ProductDto[].class));
    }

    public ProductDto buyProduct(long id, int quantity) {

        Map<String, String> variables = new HashMap<>();
        variables.put("id", Long.toString(id));
        variables.put("quantity", Integer.toString(quantity));

        return restTemplate.postForObject(
                "/api/products/product/{id}/retrieve/{quantity}",
                null,
                ProductDto.class,
                variables);
    }
}
