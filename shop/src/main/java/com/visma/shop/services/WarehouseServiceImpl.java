package com.visma.shop.services;

import com.visma.warehousedto.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import javax.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final RestTemplate restTemplate;

    @Value("${warehouse.baseUrl}")
    private String warehouseBaseUrl;

    public WarehouseServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void init(){
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(warehouseBaseUrl));
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
    }

    public List<ProductDto> getAllProducts() {
        return Arrays.asList(
                restTemplate.getForObject(
                        "/api/products",
                        ProductDto[].class));
    }

    public Resource getReport(LocalDateTime date){
        Map<String, String> variables = new HashMap<>();
        variables.put("date", date.truncatedTo(ChronoUnit.HOURS).format(DateTimeFormatter.ofPattern("yyyy.MM.dd'T'HH")));
        return restTemplate.getForObject("/api/report/csv/{date}",
                Resource.class,
                variables);
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
