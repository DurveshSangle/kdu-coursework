package com.kdu.smarthome.dto.request;

import com.kdu.smarthome.entities.Devices;
import lombok.Data;

@Data
public class DevicesDto {
    String kickston_id;
    String device_username;
    String device_password;
    String manufacture_date_time;
    String manufacture_factory_place;

    public Devices toDevices(){
        Devices device = new Devices();
        device.setKickston_id(kickston_id);
        device.setDevice_username(device_username);
        device.setDevice_password(device_password);
        device.setManufacture_date_time(manufacture_date_time);
        device.setManufacture_factory_place(manufacture_factory_place);
        return device;
    }
}
