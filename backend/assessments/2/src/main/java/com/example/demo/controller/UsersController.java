package com.example.demo.controller;

import com.example.demo.entities.Address;
import com.example.demo.entities.CartItem;
import com.example.demo.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UsersController {
    UsersServices usersServices;
    @Autowired
    public UsersController(UsersServices usersServices) {
        this.usersServices = usersServices;
    }
    @PostMapping("/address/add")
    public ResponseEntity<String> addAddress(@RequestBody Address address){
        usersServices.addAddress(address);
        return ResponseEntity.ok("added address");
    }

    @PutMapping("/address/update/{addressId}")
    public ResponseEntity<String> updateAddress(@RequestBody Address address, @PathVariable String addressId){
        UUID addressUUID = UUID.fromString(addressId);
        usersServices.updateAddress(addressUUID,address);
        return ResponseEntity.ok("updated address");
    }

    @DeleteMapping("/address/delete/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable String addressId){
        UUID addressUUID = UUID.fromString(addressId);
        usersServices.deleteAddress(addressUUID);
        return ResponseEntity.ok("delete address");
    }

    @PostMapping("/cart/add/{productId}/{userId}")
    public ResponseEntity<String> addProductToCart(@PathVariable String productId, @PathVariable String userId){
        UUID productUUID = UUID.fromString(productId);
        UUID userUUID = UUID.fromString(userId);
        usersServices.addProductToCart(productUUID,userUUID);
        return ResponseEntity.ok("delete address");
    }

    @GetMapping("/cart/add/{productId}/{userId}")
    public List<CartItem> seeCart(@PathVariable String userId){
        UUID userUUID = UUID.fromString(userId);
        return usersServices.seeCart(userUUID);
    }
}
