package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    UUID id;
    @Column(columnDefinition = "varchar(32)")
    String name;
    @Column(columnDefinition = "varchar(32)")
    String description;
    @Column(columnDefinition = "int")
    int price;
    @Column(columnDefinition = "int")
    int quantity;
}
