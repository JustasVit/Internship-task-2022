package com.visma.warehouse.services;

import com.visma.warehouse.models.Product;
import com.visma.warehouse.models.Shop;
import com.visma.warehouse.models.ShopProduct;
import com.visma.warehouse.repositories.ProductRepository;
import com.visma.warehouse.repositories.ShopProductRepository;
import com.visma.warehouse.repositories.ShopRepository;
import com.visma.warehousedto.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Profile("database")
@AllArgsConstructor
public class ProductServiceDatabaseImpl implements ProductService{

    private ProductRepository productRepository;
    private ShopRepository shopRepository;
    private ShopProductRepository shopProductRepository;

    public List<ProductDto> getAllProducts(){
        return productRepository
                .findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public ProductDto buyProduct(long id, int quantity){
        Shop loggedInShop = getCurrentlyLoggedInShop();
        Product productToBuy = productRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product doesn't exist!"));

        productToBuy.setQuantity(productToBuy.getQuantity() - quantity);
        productRepository.save(productToBuy);

        ShopProduct shopProduct = new ShopProduct(null, loggedInShop, productToBuy, quantity, LocalDateTime.now());
        shopProductRepository.save(shopProduct);

        return convertToDto(productToBuy);
    }

    private ProductDto convertToDto(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        return productDto;
    }
    private Product convertToEntity(ProductDto productDto){
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        return product;
    }
    private Shop getCurrentlyLoggedInShop(){
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return shopRepository
                    .findShopByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
        } else {
            return shopRepository
                    .findShopByUsername(principal.toString())
                    .orElseThrow(() -> new UsernameNotFoundException(principal.toString()));
        }
    }
}
