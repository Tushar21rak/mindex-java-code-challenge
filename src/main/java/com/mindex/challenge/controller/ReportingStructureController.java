package com.mindex.challenge.controller;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/reporting-structure")
public class ReportingStructureController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    ReportingStructureService reportingStructureService;

    @GetMapping("/{employeeId}")
    public ResponseEntity<ReportingStructure> getReportingStructure(@PathVariable String employeeId){
        LOG.debug("Received total number of report request for Employee ID [{}]", employeeId);
        ReportingStructure reportingStructure = new ReportingStructure();
        return new ResponseEntity<ReportingStructure>(reportingStructureService.getReportingStructure(employeeId), HttpStatus.OK);
    }

}
