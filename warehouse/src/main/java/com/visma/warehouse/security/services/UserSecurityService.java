package com.visma.warehouse.security.services;

import com.visma.warehouse.models.Shop;
import com.visma.warehouse.repositories.ShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Profile("database")
@AllArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private ShopRepository shopRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Shop shop = shopRepository
                .findShopByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        UserDetails user = User
                .withUsername(shop.getUsername())
                .password(shop.getPassword())
                .authorities("USER")
                .build();

        return user;
    }

    public Shop getCurrentlyLoggedInShop(){
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username = extractUsername(principal);

        return shopRepository
                .findShopByUsername(extractUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private String extractUsername(Object principal){
        return principal instanceof UserDetails ?
                ((UserDetails) principal).getUsername() :
                principal.toString();
    }
}
