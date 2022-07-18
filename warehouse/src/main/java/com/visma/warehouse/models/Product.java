package com.visma.warehouse.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Product")
public class Product {

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NonNull
    private String name;

    @Setter
    private String description;

    @NonNull
    private BigDecimal price;

    @NonNull
    private int quantity;
    @Setter
    @OneToMany(mappedBy = "product")
    List<ShopProduct> shopHistory;

    public Product(long id, String name, String description, BigDecimal price, int quantity, List<ShopProduct> shopHistory){
        this.id = id;
        this.name = name;
        this.description = description;
        setPrice(price);
        setQuantity(quantity);
        this.shopHistory = shopHistory;
    }

    public void setPrice(BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be bigger than 0!");
        }
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if(quantity < 0) {
            throw new IllegalArgumentException("Product quantity can't be negative!");
        }
        this.quantity = quantity;
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
