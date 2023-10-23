package com.example.webshopbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * User Class
 *
 * This class represents a user in the webshop system. It stores user-related information, including name, surname, address (street, house number, postal code, and country), email, password, and user role.
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id; // Unique identifier for the user

    @NotBlank
    private String name; // The user's first name

    @NotBlank
    private String surname; // The user's last name

    @NotBlank
    private String street; // The street address of the user

    @NotBlank
    private String housenumber; // The house number of the user's address

    @NotBlank
    private String postalcode; // The postal code of the user's address

    @NotBlank
    private String country; // The country of the user's address

    @Email
    @NotBlank
    @Column(unique = true)
    private String email; // The user's email address, which is unique

    @NotBlank(message = "Password must not be blank")
    private String password; // The user's password

    @Enumerated(EnumType.STRING)
    private UserRole role; // The role of the user (e.g., USER, ADMIN)

    /////////////////////////////////////////////////////////
    // Getters and setters for the above attributes
    /////////////////////////////////////////////////////////

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}



