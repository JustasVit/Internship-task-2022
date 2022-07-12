package com.visma.warehouse.models;

import com.visma.warehouse.dto.ProductDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@Getter
public class Product {

    @NonNull
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

    public Product(long id, String name, String description, BigDecimal price, int quantity){
        this.id = id;
        this.name = name;
        this.description = description;
        if(price.compareTo(BigDecimal.ZERO) == 1){
            this.price = price;
        } else throw new IllegalArgumentException("Product price must be bigger than 0!");
        if(quantity >= 0){
            this.quantity = quantity;
        } else throw new IllegalArgumentException("Product quantity can't be negative!");
    }

    public Product(ProductDto productDto){
        this.id = productDto.getId();
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.quantity = productDto.getQuantity();
    }

    public void setPrice(BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO) == 1) {
            this.price = price;
        } else throw new IllegalArgumentException("Product price must be bigger than 0!");
    }

    public void setQuantity(int quantity) {
        if(quantity >= 0) {
            this.quantity = quantity;
        } else throw new IllegalArgumentException("Product quantity can't be negative!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getId() == product.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
