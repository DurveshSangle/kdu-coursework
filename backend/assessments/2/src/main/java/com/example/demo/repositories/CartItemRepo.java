package com.example.demo.repositories;

import com.example.demo.entities.CartItem;
import com.example.demo.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CartItemRepo extends JpaRepository<CartItem, UUID> {
    List<CartItem> findByCart(ShoppingCart cart);
}
