package com.visma.warehouse.repositories;

import com.visma.warehouse.models.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShopProductRepository extends JpaRepository<ShopProduct,Long> {
    List<ShopProduct> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
