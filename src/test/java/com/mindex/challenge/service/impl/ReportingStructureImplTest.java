package com.mindex.challenge.service.impl;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureImplTest {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ReportingStructureService reportingStructureService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String structureReportingUrl;
    private String employeeUrl;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        structureReportingUrl = "http://localhost:" + port + "/reporting-structure/{employeeId}";
    }

    @Test
    public void getReportingStructureTest(){
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("Tim");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        Employee reportingEmployee1 = new Employee();
        reportingEmployee1.setFirstName("John");
        reportingEmployee1.setLastName("Doe");
        reportingEmployee1.setDepartment("Engineering");
        reportingEmployee1.setPosition("Developer");
        reportingEmployee1.setEmployeeId("EMP456");

        Employee reportingEmployee2 = new Employee();
        reportingEmployee2.setFirstName("John");
        reportingEmployee2.setLastName("Doe");
        reportingEmployee2.setDepartment("Engineering");
        reportingEmployee2.setPosition("Developer");
        reportingEmployee2.setEmployeeId("EMP789");

        testEmployee.setDirectReports(Lists.newArrayList(reportingEmployee1, reportingEmployee2));

        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setNumberOfReports(testEmployee.getDirectReports().size());
        reportingStructure.setEmployeeID(testEmployee.getEmployeeId());

        // Create employee
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();

        // get numberOfReports
        ReportingStructure readReport = restTemplate.getForEntity(structureReportingUrl, ReportingStructure.class, createdEmployee.getEmployeeId()).getBody();
        assertReportingCountEquivalence(readReport,testEmployee );
    }

    private void assertReportingCountEquivalence(ReportingStructure readReport, Employee testEmployee) {
        assertEquals(readReport.getNumberOfReports() , testEmployee.getDirectReports().size());
    }

}
