package com.example.springjdbc.controller;

import com.example.springjdbc.dto.AllDTO;
import com.example.springjdbc.exceptions.custom.FailedTransactionRollBackException;
import com.example.springjdbc.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/global")
public class GlobalController {
    private final AllDataService allDataService;
    @Autowired
    public GlobalController(AllDataService allDataService) {
        this.allDataService = allDataService;
    }
    @PostMapping("insert")
    public ResponseEntity<String> insertAllAtOnce(@RequestBody AllDTO allDTO) throws FailedTransactionRollBackException {
        allDataService.insertAllData(allDTO);
        return ResponseEntity.ok("All entities inserted in db successfully");
    }
}
