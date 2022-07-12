package com.visma.warehouse.services;

import com.visma.warehouse.models.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getAllProducts(){
        ArrayList<Product> products = new ArrayList<>(List.of(
                new Product(1,
                        "Samsung smart TV",
                        "Smart TV made by Samsung",
                        new BigDecimal("599.99" ),
                        10),
                new Product(2,
                        "Gaming laptop \"Lenovo Legion Y530\"",
                        "Gaming laptop made by Lenovo",
                        new BigDecimal("799.99" ),
                        10),
                new Product(3,
                        "Smartphone \"Apple Iphone Pro Max\", 256GB ",
                        "Smartphone made by Apple",
                        new BigDecimal("999.99" ),
                        10)
                ));
        return products;
    }

    public Product buyProduct(long id, int quantity){
        return new Product(1,
                "Samsung smart TV",
                "Smart TV made by Samsung",
                new BigDecimal("599.99" ),
                9);
    }
}
