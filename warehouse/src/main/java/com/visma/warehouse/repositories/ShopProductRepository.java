package com.visma.warehouse.repositories;

import com.visma.warehouse.models.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopProductRepository extends JpaRepository<ShopProduct,Long> {
}
