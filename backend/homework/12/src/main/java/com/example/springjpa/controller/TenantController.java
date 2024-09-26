package com.example.springjpa.controller;

import com.example.springjpa.entities.Tenants;
import com.example.springjpa.services.TenantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tenant")
public class TenantController {
    private TenantServices tenantServices;
    @Autowired
    public TenantController(TenantServices tenantServices) {
        this.tenantServices = tenantServices;
    }

    @PostMapping("/save")
    public ResponseEntity<String> inserttenant(@RequestBody Tenants tenant){
        tenantServices.saveTenant(tenant);
        return ResponseEntity.ok("added success");
    }
}
