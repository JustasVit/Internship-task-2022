package com.visma.shop.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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