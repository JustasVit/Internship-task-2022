package com.visma.shop.controllers;

import com.visma.shop.services.WarehouseService;
import com.visma.warehousedto.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/shop")
@AllArgsConstructor
public class ShopController {

    private WarehouseService warehouseService;

    @GetMapping("/products")
    public ModelAndView getAllProducts(){
        ModelAndView modelAndView = new ModelAndView("product-list");
        List<ProductDto> products = warehouseService.getAllProducts();
        modelAndView.addObject("products", products);
        return  modelAndView;
    }

    @GetMapping("/report/csv")
    public ResponseEntity<Resource> getReport() {
        Resource resource = warehouseService.getReport(LocalDateTime.now());
        String filename = String.format("%s.csv",LocalDateTime.now().truncatedTo(ChronoUnit.HOURS));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(resource);
    }

    @PostMapping("/product/buy/{id}")
    public String buyProductPost(@PathVariable Long id, @RequestParam int quantity){
        warehouseService.buyProduct(id, quantity);
        return "redirect:/shop/products";
    }

}
