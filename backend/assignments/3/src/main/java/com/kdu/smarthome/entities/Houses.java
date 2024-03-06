package com.kdu.smarthome.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Houses {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    UUID id;
    @Column(columnDefinition = "text")
    String address;
    @Column(columnDefinition = "varchar(25)")
    String house_name;
    @ManyToMany(cascade = CascadeType.ALL)
    List<Users> usersList;
    @OneToOne(cascade = CascadeType.ALL)
    Users adminUser;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Rooms> roomsList = new ArrayList<>();
}
