package com.kdu.smarthome.entities;


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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    @Column(columnDefinition = "varchar(32)")
    private String username;
    @Column(columnDefinition = "text")
    private String password;
    @Column(columnDefinition = "varchar(50)")
    private String name;
    @Column(columnDefinition = "varchar(25)")
    private String firstName;
    @Column(columnDefinition = "varchar(25)")
    private String lastName;
    @Column(columnDefinition = "varchar(50)")
    private String emailId;
}
