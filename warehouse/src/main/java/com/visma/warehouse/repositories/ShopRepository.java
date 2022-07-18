package com.visma.warehouse.repositories;

import com.visma.warehouse.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ShopRepository extends JpaRepository<Shop,Long> {

    Shop findShopByUsername(String username);
}
