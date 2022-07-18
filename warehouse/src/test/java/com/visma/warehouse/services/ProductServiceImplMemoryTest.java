package com.visma.warehouse.services;

import com.visma.warehousedto.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@ActiveProfiles("memory")
public class ProductServiceImplMemoryTest {

    private ProductServiceImplMemory productService;

    @BeforeEach
    public void init(){
        productService = new ProductServiceImplMemory();
    }

    @Test
    public void ProductServiceImplTest_getAllProducts_ShouldReturnThreeProducts(){
        assertThat(productService.getAllProducts().size()).isEqualTo(3);
    }

    @Test
    public void ProductServiceImplTest_getAllProducts_ProductsShouldntBeNull(){
        assertThat(productService.getAllProducts())
                .asList()
                .allSatisfy(p -> assertThat(p).isNotNull());
    }

    @Test
    public void ProductServiceImplTest_buyProduct_ProductQuantityShouldBeReduced(){
        ProductDto productDto = productService.buyProduct(1,5);
        ProductDto boughtProduct = productService
                .getAllProducts()
                .stream()
                .filter(p -> p.getId() == 1)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException());

        assertThat(boughtProduct.getQuantity()).isEqualTo(5);

    }

    @Test
    public void ProductServiceImplTest_buyProduct_ShouldThrowNoSuchElementException(){
        assertThatThrownBy(() -> productService.buyProduct(5,5) )
                .isInstanceOf(NoSuchElementException.class);
    }
}
