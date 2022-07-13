package com.visma.warehouse.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    @NonNull
    private long id;

    @NonNull
    @Getter
    private int quantity;
}
