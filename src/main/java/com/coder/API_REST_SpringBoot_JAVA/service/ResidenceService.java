package com.coder.API_REST_SpringBoot_JAVA.service;

import com.coder.API_REST_SpringBoot_JAVA.models.Residence;
import com.coder.API_REST_SpringBoot_JAVA.repository.ResidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenceService {
    @Autowired
    private ResidenceRepository residenceRepository;

    public Residence saveResidence(Residence residence) {
        return residenceRepository.save(residence);
    }
}
