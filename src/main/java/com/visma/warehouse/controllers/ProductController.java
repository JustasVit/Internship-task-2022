package com.visma.warehouse.controllers;

import com.visma.warehouse.dto.ProductDto;
import com.visma.warehouse.dto.ProductRequestDto;
import com.visma.warehouse.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> retrieveAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductDto> buyProduct(@RequestBody ProductRequestDto productRequestDto){
        return ResponseEntity.ok(productService.buyProduct(productRequestDto.getId(), productRequestDto.getQuantity()));
    }
}
