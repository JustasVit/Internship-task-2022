package com.visma.shop.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {

    @NonNull
    private long id;

    @NonNull
    @Setter
    private String name;

    @NonNull
    @Setter
    private String address;

    @NonNull
    @Setter
    private String phoneNumber;
}
