package com.visma.shop.dto;

import com.visma.shop.models.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    @NonNull
    private long id;

    @NonNull
    private String name;

    private String description;

    @NonNull
    private BigDecimal price;

    @NonNull
    private int quantity;
}