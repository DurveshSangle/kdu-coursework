package com.kdu.caching.repositories;

import com.kdu.caching.client.ApiClient;
import com.kdu.caching.dto.AddressDTO;
import com.kdu.caching.dto.CoordinatesDTO;
import com.kdu.caching.exceptions.customexceptions.InvalidAddressException;
import com.kdu.caching.exceptions.customexceptions.InvalidLatitudeLongitudeException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
@Slf4j
@Data
public class GeoRepository implements CacheManagerCustomizer<ConcurrentMapCacheManager> {
    ApiClient apiClient;

    @Autowired
    GeoRepository(ApiClient apiClient){
        this.apiClient = apiClient;
    }
    @Override
    public void customize(ConcurrentMapCacheManager cacheManager) {
        cacheManager.setCacheNames(Arrays.asList("geocoding", "reverse-geocoding"));
    }

    /**
     * Forward geocoding method which checks cache first, if not found then makes api call via apiClient.
     * Any address with word goa would not be cached.
     * @param address Address of the location whose coordinates are to be found
     * @return Array of coordinates of all such possible addresses.
     * */
    @Cacheable(value="geocoding",condition="!#address.toLowerCase().contains('goa')")
    public CoordinatesDTO[] forwardGeocoding(String address) throws InvalidAddressException {
        log.debug("forward geocoding method call to apiClient");
        return apiClient.forwardEncodingApiCall(address);
    }

    /**
     * Reverse geocoding method which checks cache first, if not found then makes api call via apiClient.
     * @param latitude Latitude of the location whose address is to be found
     * @param longitude Longitude of the location whose address is to be found
     * @return Array of all addresses mapped to that particular coordinates.
     * */
    @Cacheable("reverse-geocoding")
    public AddressDTO[] reverseGeocoding(double latitude,double longitude) throws InvalidLatitudeLongitudeException {
        log.debug("reverse geocoding method call to apiClient");
        return apiClient.reverseEncodingApiCall(latitude,longitude);
    }
}
