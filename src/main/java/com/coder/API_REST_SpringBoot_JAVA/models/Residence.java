package com.coder.API_REST_SpringBoot_JAVA.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Residence")
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String country;
    private String city;
    private String street;
    private int house_number;
    private String postal_code;

    @OneToOne(mappedBy = "residence")
    private User user;

}
