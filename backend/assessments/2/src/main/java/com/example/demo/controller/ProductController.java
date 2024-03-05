package com.example.demo.controller;

import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.ok("added product");
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable String id, @RequestBody Product product){
        UUID uuid = UUID.fromString(id);
        productService.updateProduct(uuid,product);
        return ResponseEntity.ok("updated product");
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<String> deleteAddress(@PathVariable String Id){
        UUID uuid = UUID.fromString(Id);
        productService.deleteProduct(uuid);
        return ResponseEntity.ok("deleted address");
    }
}
