package com.visma.shop.models;

import lombok.*;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    @NonNull
    private long id;

    @Setter
    private String name;

    @Setter
    private String address;

    @Setter
    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return getId() == shop.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
