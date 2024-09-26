package com.kdu.caching.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDTO {
    @JsonProperty("name")
    String name;
    @JsonProperty("number")
    String number;
    @JsonProperty("postal_code")
    String postalCode;
    @JsonProperty("street")
    String street;
    @JsonProperty("region")
    String region;
    @JsonProperty("county")
    String county;
    @JsonProperty("country")
    String country;

    @Override
    public String toString(){
        return number+", "+name+", "+region+", "+country+", "+postalCode;
    }
}
