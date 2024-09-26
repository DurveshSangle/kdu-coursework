package com.kdu.caching.controller;

import com.kdu.caching.dto.AddressDTO;
import com.kdu.caching.dto.CoordinatesDTO;
import com.kdu.caching.exceptions.customexceptions.InvalidAddressException;
import com.kdu.caching.exceptions.customexceptions.InvalidLatitudeLongitudeException;
import com.kdu.caching.services.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GeocodingController {
    LocationService locationService;

    @Autowired
    GeocodingController(LocationService locationService){
        this.locationService = locationService;
    }

    /**
     * Forward Geocoding API call
     * */
    @GetMapping("/geocoding")
    public ResponseEntity<CoordinatesDTO> forwardGeocoding(@RequestParam String address) throws InvalidAddressException {
        CoordinatesDTO[] allCoordinatesDTO = locationService.forwardGeocoding(address);
        log.debug("forward geocoding method call to location service");
        log.info("forward geocoding call successful for address: "+address);
        return ResponseEntity.ok(allCoordinatesDTO[0]);
    }

    /**
     * Reverse Geocoding API call
     * */
    @GetMapping("/reverse-geocoding")
    public ResponseEntity<String> reverseGeocoding(@RequestParam double latitude , @RequestParam double longitude) throws InvalidLatitudeLongitudeException {
        AddressDTO[] addressDTO = locationService.reverseGeocoding(latitude,longitude);
        log.debug("forward geocoding method call to location service");
        log.info("reverse geocoding call successful for latitude: "+latitude+" and longitude: "+longitude);
        return ResponseEntity.ok(addressDTO[0].toString());
    }
}
