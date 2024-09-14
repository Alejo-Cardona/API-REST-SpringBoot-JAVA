package com.coder.API_REST_SpringBoot_JAVA.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "USERS" )
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "First name is mandatory")
    private String first_name;
    @NotBlank(message = "Last name is mandatory")
    private String last_name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    private int age;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    @NotBlank(message = "DNI is mandatory")
    private String dni;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "residence_id")
    private Residence residence;

    /*
    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;
     */

    // Constructor default JPA
    public User(){
    }

    // Constructor
    public User(String first_name, String last_name, String email, int age, String password, String dni) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.age = age;
        this.password = password;
        this.dni = dni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(first_name, user.first_name) && Objects.equals(last_name, user.last_name) && Objects.equals(email, user.email) && Objects.equals(age, user.age) && Objects.equals(password, user.password) && Objects.equals(dni, user.dni) && Objects.equals(residence, user.residence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, email, age, password, dni, residence);
    }

    @Override
    public String toString() {
        return "User{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", password='" + password + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }
}
