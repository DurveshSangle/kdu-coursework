package com.kdu.smarthome.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Devices {
    @Id
    String kickston_id;
    String device_username;
    String device_password;
    String manufacture_date_time;
    String manufacture_factory_place;
    boolean registered;
}
