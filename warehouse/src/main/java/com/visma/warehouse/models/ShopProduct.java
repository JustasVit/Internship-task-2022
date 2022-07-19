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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Setter
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Setter
    @NonNull
    private int quantity;

    @Setter
    @NonNull
    private LocalDateTime date;
}
