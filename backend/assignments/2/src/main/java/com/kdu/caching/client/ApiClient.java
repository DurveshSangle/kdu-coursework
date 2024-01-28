package com.kdu.caching.client;

import com.kdu.caching.dto.AddressDTO;
import com.kdu.caching.dto.CoordinatesDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdu.caching.exceptions.customexceptions.InvalidAddressException;
import com.kdu.caching.exceptions.customexceptions.InvalidLatitudeLongitudeException;
import com.kdu.caching.exceptions.customexceptions.UnableToDeserializeJSONException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ApiClient {
    @Value("${api-key}")
    private String apiAccessKey;
    @Value("${api.url}")
    private String apiUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    ApiClient(RestTemplate restTemplate,ObjectMapper objectMapper){
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * Function to call position stack api for forward geocoding
     * @param address Address of the location whose latitude and longitude it be found
     * @return Array of coordinates for all such possible addresses
     * */
    public CoordinatesDTO[] forwardEncodingApiCall(String address){
        String fullUrl = apiUrl + "/forward?access_key="+apiAccessKey+"&query="+address;
        String apiResponse = restTemplate.getForObject(fullUrl, String.class);
        CoordinatesDTO[] coordinatesDTO;
        try{
            JsonNode jsonNode = objectMapper.readTree(apiResponse);
            JsonNode dataArray = jsonNode.get("data");
            if(dataArray.isEmpty()) {
                log.error("The provided address is invalid");
                throw new InvalidAddressException("Invalid Address");
            }
            coordinatesDTO = objectMapper.treeToValue(dataArray, CoordinatesDTO[].class);
            log.debug("Position Stack API call for forward geocoding successful.");
        }catch (Exception e){
            log.error("Error deserializing JSON response of forward geocoding");
            throw new UnableToDeserializeJSONException("Error deserializing JSON response in forward geocoding",e);
        }
        return coordinatesDTO;
    }
    /**
     * Function to call position stack api for reverse geocoding
     * @param latitude Latitude coordinate of address to be found
     * @param longitude Longitude coordinate of address to be found
     * @return Array of addresses for all such possible latitude and longitude
     * */
    public AddressDTO[] reverseEncodingApiCall(double latitude, double longitude){
        String fullUrl = apiUrl + "/reverse?access_key="+apiAccessKey+"&query="+latitude+","+longitude;
        String apiResponse = restTemplate.getForObject(fullUrl, String.class);
        AddressDTO[] addressDTO;
        try {
            JsonNode jsonNode = objectMapper.readTree(apiResponse);
            JsonNode dataArray = jsonNode.get("data");
            if(dataArray.isEmpty()){
                log.error("The provided latitude and longitudes are invalid");
                throw new InvalidLatitudeLongitudeException("Invalid Latitude and Longitudes");
            }
            addressDTO = objectMapper.treeToValue(dataArray, AddressDTO[].class);
            log.debug("Position Stack API call for reverse geocoding successful.");
        } catch (Exception e) {
            log.error("Error deserializing JSON response of reverse gecoding");
            throw new UnableToDeserializeJSONException("Error deserializing JSON response in reverse geocoding", e);
        }
        return addressDTO;
    }
}
