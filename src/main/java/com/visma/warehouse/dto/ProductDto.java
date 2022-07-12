package com.visma.warehouse.dto;

import com.visma.warehouse.models.Product;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
public class ProductDto {

    private long id;

    @Setter
    @NonNull
    private String name;

    @Setter
    private String description;

    @NonNull
    private BigDecimal price;

    @NonNull
    private int quantity;

    public ProductDto(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }
}