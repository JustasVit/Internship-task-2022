package com.visma.warehouse.repositories;

import com.visma.warehouse.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
