package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    UUID id;
    @Column(columnDefinition = "varchar(32)")
    String street;
    @Column(columnDefinition = "varchar(32)")
    String city;
    @Column(columnDefinition = "varchar(32)")
    String state;
    @Column(columnDefinition = "varchar(32)")
    String country;
    @Column(columnDefinition = "varchar(10)")
    String postalCode;
    @Column(columnDefinition = "varchar(32)")
    String nickName;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    Users user;
}
