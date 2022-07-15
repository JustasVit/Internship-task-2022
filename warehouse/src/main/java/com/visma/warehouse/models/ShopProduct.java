package com.visma.warehouse.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Shop_Product")
public class ShopProduct {

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "shop_id")
    Shop shop;

    @Setter
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @Setter
    @NonNull
    int quantity;

    @Setter
    @NonNull
    LocalDateTime date;
}
