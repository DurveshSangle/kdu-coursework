package com.example.demo.services;

import com.example.demo.entities.Product;
import com.example.demo.repositories.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {
    Inventory inventory;
    @Autowired
    public ProductService(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addProduct(Product product){
        inventory.save(product);
    }

    public void deleteProduct(UUID id){
        Product product = inventory.findById(id).get();
        inventory.delete(product);
    }

    public void updateProduct(UUID id,Product product){
        Product ep = inventory.findById(id).get();
        ep.setDescription(product.getDescription());
        ep.setName(product.getName());
        ep.setPrice(product.getPrice());
        ep.setQuantity(product.getQuantity());
        inventory.save(ep);
    }
}
