package com.visma.shop.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    @NonNull
    private long id;

    @NonNull
    @Setter
    private int quantity;
}
