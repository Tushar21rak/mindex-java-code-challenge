package com.mindex.challenge.service.impl;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    @Autowired
    CompensationService compensationService;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String createCompensationUrl;
    private String getCompensationUrl;

    @Before
    public void setup() {
        createCompensationUrl = "http://localhost:" + port + "/compensation";
        getCompensationUrl = "http://localhost:" + port + "/compensation/{employeeId}";

    }

    @Test
    public void testCreateReadUpdate() {
        Compensation compensation = new Compensation();
        compensation.setEmployee(UUID.randomUUID().toString());
        compensation.setSalary(12345);
        compensation.setEffectiveDate(LocalDate.now().minusDays(180));

        // create Compensation
        Compensation createdCompensation = restTemplate.postForEntity(createCompensationUrl, compensation, Compensation.class).getBody();
        assertNotNull(createdCompensation.getEmployee());

        // read Compensation
        Compensation readCompensation = restTemplate.getForEntity(getCompensationUrl, Compensation.class, compensation.getEmployee()).getBody();
        assertEquals(createdCompensation.getEmployee(), readCompensation.getEmployee());
    }
}
