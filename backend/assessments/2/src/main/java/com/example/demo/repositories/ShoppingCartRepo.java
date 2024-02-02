package com.example.demo.repositories;

import com.example.demo.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, UUID> {

}
