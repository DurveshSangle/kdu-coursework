package com.kdu.smarthome.dto.request;

import lombok.Data;

@Data
public class DeviceAddDto {
    String houseId;
    String roomId;
    String kickstonId;
}
