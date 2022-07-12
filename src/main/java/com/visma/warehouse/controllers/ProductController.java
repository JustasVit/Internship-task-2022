package com.visma.warehouse.controllers;

import com.visma.warehouse.dto.ProductDto;
import com.visma.warehouse.dto.ProductRequestDto;
import com.visma.warehouse.models.Product;
import com.visma.warehouse.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> retrieveAllProducts(){
        return new ResponseEntity<>(productService
                .getAllProducts()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> buyProduct(@RequestBody ProductRequestDto productRequestDto){
        Product boughtProduct = productService.buyProduct(productRequestDto.getId(), productRequestDto.getQuantity());
        if(boughtProduct != null){
            return new ResponseEntity<>(convertToDto(boughtProduct),HttpStatus.OK);
        } else return new ResponseEntity<>(null,HttpStatus.CONFLICT);

    }
    private ProductDto convertToDto(Product product){
        return new ProductDto(product);
    }
    private Product convertToEntity(ProductDto productDto){
        return new Product(productDto);
    }
}
