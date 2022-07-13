package com.visma.warehouse.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductTest {

    @Test
    public void productTest_NegativePrice_ShouldThrowException(){
        assertThatThrownBy(() ->new Product(1,"name","description",new BigDecimal("-8"),1) )
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void productTest_NegativeQuantity_ShouldThrowException(){
        assertThatThrownBy(() ->new Product(1,"name","description",new BigDecimal("1"),-1) )
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void productPriceSetterTest_NegativePrice_ShouldThrowException(){
        Product product = new Product(1,"name","description",new BigDecimal("1"),1);
        assertThatThrownBy(() -> product.setPrice(new BigDecimal("-1")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void productPriceSetterTest_NegativeQuantity_ShouldThrowException(){
        Product product = new Product(1,"name","description",new BigDecimal("1"),1);
        assertThatThrownBy(() -> product.setQuantity(-8))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void productTest_ShouldPass(){
        Product product = new Product(1,"name","description",new BigDecimal("1"),1);
        assertThat(product).isNotNull();

    }
}
