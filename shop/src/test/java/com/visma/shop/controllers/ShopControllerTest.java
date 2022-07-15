package com.visma.shop.controllers;

import com.visma.shop.services.WarehouseService;
import com.visma.warehousedto.dto.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ShopController.class)
public class ShopControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WarehouseService warehouseService;

    @Test
    public void getAllProductsTest() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/shop")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void buyProductTest() throws Exception {

        ProductDto productDto = new ProductDto();
        Mockito.when(warehouseService.buyProduct(Mockito.anyLong(), Mockito.anyInt())).thenReturn(productDto);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/shop/product/1/sell/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}