package com.kdu.caching.services;

import com.kdu.caching.dto.AddressDTO;
import com.kdu.caching.dto.CoordinatesDTO;
import com.kdu.caching.exceptions.customexceptions.InvalidAddressException;
import com.kdu.caching.exceptions.customexceptions.InvalidLatitudeLongitudeException;
import com.kdu.caching.repositories.GeoRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
@Slf4j
public class LocationService {
    private final GeoRepository geoRepository;
    @Autowired
    public LocationService(GeoRepository geoRepository){
        this.geoRepository = geoRepository;
    }

    /** Forward geocoding method call to repository */
    public CoordinatesDTO[] forwardGeocoding(String address) throws InvalidAddressException {
        log.debug("Method call for forward geocoding");
        return geoRepository.forwardGeocoding(address);
    }
    /** Reverse geocoding method call to repository */
    public AddressDTO[] reverseGeocoding(double latitude,double longitude) throws InvalidLatitudeLongitudeException {
        log.debug("Method call for reverse geocoding");
        return geoRepository.reverseGeocoding(latitude, longitude);
    }
}
