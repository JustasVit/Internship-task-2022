package com.visma.shop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    @NonNull
    private long id;

    @NonNull
    private int quantity;
}
