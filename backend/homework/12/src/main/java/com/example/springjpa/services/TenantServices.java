package com.example.springjpa.services;

import com.example.springjpa.entities.Tenants;
import com.example.springjpa.repositories.TenantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TenantServices {
    private TenantRepository tenantRepository;
    @Autowired
    public TenantServices(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }
    public void saveTenant(Tenants tenant){
        tenantRepository.save(tenant);
        log.info("Tenant saved successfully");
    }
}
