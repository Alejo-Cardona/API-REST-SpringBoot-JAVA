package com.coder.API_REST_SpringBoot_JAVA.repository;

import com.coder.API_REST_SpringBoot_JAVA.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> { }
