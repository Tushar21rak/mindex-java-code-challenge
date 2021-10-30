package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compensation")
public class CompensationController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    CompensationService compensationService;

    @PostMapping
    public ResponseEntity<Compensation> create(@RequestBody Compensation compensation){
        LOG.debug(" Received Compensation create request with compensation : [{}]", compensation);
        return new ResponseEntity<Compensation>(compensationService.create(compensation), HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Compensation> readCompensation(@PathVariable String employeeId) throws Exception {
        LOG.debug(" Received Compensation read request for employee Id : [{}]", employeeId);
        return new ResponseEntity<Compensation>(compensationService.readCompensation(employeeId) , HttpStatus.OK);
    }
}
