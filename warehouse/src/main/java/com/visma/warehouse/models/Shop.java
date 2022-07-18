package com.visma.warehouse.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Shop")
public class Shop {

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NonNull
    private String username;

    @Setter
    @NonNull
    private String password;

    @Setter
    @OneToMany(mappedBy = "shop")
    List<ShopProduct> shopHistory;
}
