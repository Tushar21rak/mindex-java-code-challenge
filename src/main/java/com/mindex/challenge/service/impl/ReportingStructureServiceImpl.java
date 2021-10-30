package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    private List<String> employeeIdList = new ArrayList<>();

    @Autowired
    EmployeeService employeeService;

    @Override
    public ReportingStructure getReportingStructure(String employeeID){
        ReportingStructure reportingStructure = new ReportingStructure();
        Employee employee = employeeService.read(employeeID);
        if(Objects.nonNull(employee)){
            reportingStructure.setEmployeeID(employee.getEmployeeId());
            reportingStructure.setNumberOfReports(employee.getDirectReports().size());
        }
        return reportingStructure;
    }
}
