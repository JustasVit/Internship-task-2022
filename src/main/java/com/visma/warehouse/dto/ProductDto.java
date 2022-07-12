package com.visma.warehouse.dto;

import com.visma.warehouse.models.Product;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {

    private long id;

    @NonNull
    private String name;

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