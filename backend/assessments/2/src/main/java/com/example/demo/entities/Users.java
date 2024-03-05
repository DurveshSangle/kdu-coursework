package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    UUID id;
    @Column(columnDefinition = "varchar(64)")
    String fullName;
    @Column(columnDefinition = "varchar(32)")
    String email;
    @Column(columnDefinition = "text")
    String password;
    @Column(columnDefinition = "varchar(32)")
    String role;

    @OneToMany
    List<Address> addressList;

    @OneToOne(cascade = CascadeType.ALL)
    ShoppingCart shoppingCart;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Orders> ordersList;

    @PrePersist
    public void prePersist() {
        // Ensure default values are set before persisting
        shoppingCart = new ShoppingCart();
        addressList = new ArrayList<>();
    }
}
