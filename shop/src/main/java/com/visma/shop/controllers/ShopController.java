package com.visma.shop.controllers;

import com.visma.shop.dto.ProductDto;
import com.visma.shop.dto.ProductRequestDto;
import com.visma.shop.services.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/shop")
public class ShopController {

    private WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(warehouseService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductDto> buyProduct(@RequestBody ProductRequestDto productRequestDto){
        return ResponseEntity.ok(warehouseService.buyProduct(productRequestDto.getId(), productRequestDto.getQuantity()));
    }
}
