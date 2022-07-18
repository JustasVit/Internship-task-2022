package com.visma.warehouse.security;

import com.visma.warehouse.models.Shop;
import com.visma.warehouse.repositories.ShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Profile("database")
@AllArgsConstructor
public class SecurityConfigDatabase {

    private ShopRepository shopRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        List<UserDetails> shops = shopRepository
                .findAll()
                .stream()
                .map(this::convertShopToUserDetails)
                .collect(Collectors.toList());

        return new InMemoryUserDetailsManager(shops);
    }

    private UserDetails convertShopToUserDetails(Shop shop){
        return User
                .withUsername(shop.getUsername())
                .password(shop.getPassword())
                .roles("USER")
                .build();
    }
}