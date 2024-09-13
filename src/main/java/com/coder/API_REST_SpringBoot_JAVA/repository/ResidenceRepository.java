package com.coder.API_REST_SpringBoot_JAVA.repository;

import com.coder.API_REST_SpringBoot_JAVA.models.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResidenceRepository extends JpaRepository<Residence, UUID> { }
