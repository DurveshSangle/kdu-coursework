package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersServices {
    UsersRepo usersRepo;
    AddressRepo addressRepo;
    Inventory productRepo;
    CartItemRepo cartItemRepo;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServices(UsersRepo usersRepo, AddressRepo addressRepo, Inventory productRepo, CartItemRepo cartItemRepo, PasswordEncoder passwordEncoder) {
        this.usersRepo = usersRepo;
        this.addressRepo = addressRepo;
        this.productRepo = productRepo;
        this.cartItemRepo = cartItemRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepo.save(user);
    }

    public void addAddress(Address address){
        addressRepo.save(address);
    }

    public void updateAddress(UUID addressId, Address address){
        Address existAddress = addressRepo.findById(addressId).get();
        existAddress.setCity(address.getCity());
        existAddress.setState(address.getState());
        existAddress.setStreet(address.getStreet());
        existAddress.setCountry(address.getCountry());
        existAddress.setNickName(address.getNickName());
        addressRepo.save(address);
    }

    public void deleteAddress(UUID addressId){
        Address existAddress = addressRepo.findById(addressId).get();
        addressRepo.delete(existAddress);
    }

    public void addProductToCart(UUID productId, UUID userId){
        Product product = productRepo.findById(productId).get();
        Users user = usersRepo.findById(userId).get();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setCart(user.getShoppingCart());
        cartItemRepo.save(cartItem);
    }

    public List<CartItem> seeCart(UUID userId){
        Users user = usersRepo.findById(userId).get();
        ShoppingCart shoppingCart = user.getShoppingCart();
        return cartItemRepo.findByCart(shoppingCart);
    }
}
