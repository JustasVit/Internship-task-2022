package com.visma.warehouse.models;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
@ActiveProfiles("memory")
public class ProductTest {

    @Test
    public void productTest_NegativePrice_ShouldThrowException(){
        assertThatThrownBy(() ->new Product(1,"name","description",new BigDecimal("-8"),1,null) )
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void productTest_NegativeQuantity_ShouldThrowException(){
        assertThatThrownBy(() ->new Product(1,"name","description",new BigDecimal("1"),-1,null) )
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void productTest_ShouldPass() {
        Product product = new Product(1, "name", "description", new BigDecimal("1"), 1,null);
        assertThat(product).isNotNull();
    }
}
