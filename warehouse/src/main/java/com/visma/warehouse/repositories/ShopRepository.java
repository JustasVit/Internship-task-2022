package com.visma.warehouse.repositories;

import com.visma.warehouse.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop,Long> {

    Optional<Shop> findShopByUsername(String username);
}
