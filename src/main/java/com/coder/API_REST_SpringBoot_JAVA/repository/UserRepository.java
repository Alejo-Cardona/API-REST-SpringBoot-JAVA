package com.coder.API_REST_SpringBoot_JAVA.repository;

import com.coder.API_REST_SpringBoot_JAVA.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> { }
