package com.visma.shop.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NonNull
    private long id;

    @NonNull
    @Setter
    private String name;

    @Setter
    private String description;

    @NonNull
    @Setter
    private BigDecimal price;

    @NonNull
    @Setter
    private int quantity;
}