package com.visma.shop.controllers;

import com.visma.shop.services.WarehouseService;
import com.visma.warehousedto.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/shop")
public class RestShopController {

    private WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(warehouseService.getAllProducts());
    }

    @PostMapping("/product/{id}/sell/{quantity}")
    public ResponseEntity<ProductDto> buyProduct(@PathVariable long id, @PathVariable int quantity){
        return ResponseEntity.ok(warehouseService.buyProduct(id, quantity));
    }
}
