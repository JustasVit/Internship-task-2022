package com.visma.shop.controllers;

import com.visma.shop.services.WarehouseService;
import com.visma.warehousedto.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

}
