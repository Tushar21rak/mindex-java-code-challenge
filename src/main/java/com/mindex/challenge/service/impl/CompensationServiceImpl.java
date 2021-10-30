package com.mindex.challenge.service.impl;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    /**
     * Creates Compensation for the employee
     * @param compensation
     * @returns Compensation created object
     */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating Compensation [{}]", compensation);
        Compensation createdCompensation = compensationRepository.insert(compensation);
        return createdCompensation;
    }

    /**
     * Reads Compensation using Employee ID
     * @param employeeId
     * @return
     * @throws Exception
     */
    @Override
    public Compensation readCompensation(String employeeId) throws Exception {
        LOG.debug("Reading employee compensation by employeeId [{}]", employeeId);
        Compensation compensation =  compensationRepository.findByEmployeeId(employeeId);
        if(compensation == null){
            throw new Exception("Invalid Employee ID");
        }
        return compensation;
    }
}
