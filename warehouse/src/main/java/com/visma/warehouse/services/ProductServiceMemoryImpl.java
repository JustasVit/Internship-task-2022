package com.visma.warehouse.services;

import com.visma.warehouse.models.Product;
import com.visma.warehousedto.dto.ProductDto;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Profile("memory")
public class ProductServiceMemoryImpl implements ProductService {

    private final List<Product> products = List.of(
            new Product(1,
                    "Samsung smart TV",
                    "Smart TV made by Samsung",
                    new BigDecimal("599.99" ),
                    10,
                    null),
            new Product(2,
                    "Gaming laptop \"Lenovo Legion Y530\"",
                    "Gaming laptop made by Lenovo",
                    new BigDecimal("799.99" ),
                    10,
                    null),
            new Product(3,
                    "Smartphone \"Apple Iphone Pro Max\", 256GB ",
                    "Smartphone made by Apple",
                    new BigDecimal("999.99" ),
                    10,
                    null)
    );

    @Override
    public List<ProductDto> getAllProducts(){

        return products
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ProductDto buyProduct(long id, int quantity){
        Product product = products
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Product with id %d doesn't exist",id)));

        product.setQuantity(product.getQuantity() - quantity);
        return convertToDto(product);
    }

    private ProductDto convertToDto(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        return productDto;
    }
    private Product convertToEntity(ProductDto productDto){
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        return product;
    }
}
