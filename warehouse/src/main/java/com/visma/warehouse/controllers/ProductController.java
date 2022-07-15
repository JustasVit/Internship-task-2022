package com.visma.warehouse.controllers;

import com.visma.warehouse.services.ProductService;
import com.visma.warehousedto.dto.ProductDto;
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

    @PostMapping("/product/{id}/retrieve/{quantity}")
    public ResponseEntity<ProductDto> buyProduct(@PathVariable long id, @PathVariable int quantity){
        return ResponseEntity.ok(productService.buyProduct(id,quantity));
    }
}
