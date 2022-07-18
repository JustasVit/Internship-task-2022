package com.visma.warehouse.repositories;

import com.visma.warehouse.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("UPDATE Product SET quantity = :quantity WHERE id = :id")
    int updateProductQuantity(@Param("id") Long id, @Param("quantity") int quantity);
}
