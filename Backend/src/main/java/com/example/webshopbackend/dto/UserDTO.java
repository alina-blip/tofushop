/**
 * UserDTO Class
 *
 * This class represents a Data Transfer Object (DTO) for managing user-related information. It includes user attributes such
 * as the unique identifier, name, surname, address details, email, user role, and password.
 */
package com.example.webshopbackend.dto;

import com.example.webshopbackend.model.UserRole;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {
    /**
     * The unique identifier of the user.
     */
    private Long id;

    /**
     * The user's first name.
     */
    private String name;

    /**
     * The user's last name.
     */
    private String surname;

    /**
     * The user's street address.
     */
    private String street;

    /**
     * The user's house number.
     */
    private String housenumber;

    /**
     * The user's postal code.
     */
    private String postalcode;

    /**
     * The user's country.
     */
    private String country;

    /**
     * The user's email address.
     */
    @NotBlank
    private String email;

    /**
     * The role of the user in the system, represented as an enum value (e.g., USER, ADMIN).
     */
    private UserRole role;

    /**
     * The user's password (usually hashed for security).
     */
    private String password;

    /**
     * Get the unique identifier of the user.
     *
     * @return The unique identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the user.
     *
     * @param id The unique identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the user's first name.
     *
     * @return The first name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the user's first name.
     *
     * @param name The first name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the user's last name.
     *
     * @return The last name.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the user's last name.
     *
     * @param surname The last name to set.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Get the user's street address.
     *
     * @return The street address.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Set the user's street address.
     *
     * @param street The street address to set.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Get the user's house number.
     *
     * @return The house number.
     */
    public String getHousenumber() {
        return housenumber;
    }

    /**
     * Set the user's house number.
     *
     * @param housenumber The house number to set.
     */
    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    /**
     * Get the user's postal code.
     *
     * @return The postal code.
     */
    public String getPostalcode() {
        return postalcode;
    }

    /**
     * Set the user's postal code.
     *
     * @param postalcode The postal code to set.
     */
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    /**
     * Get the user's country.
     *
     * @return The country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the user's country.
     *
     * @param country The country to set.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get the user's email address.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the user's email address.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the role of the user.
     *
     * @return The user's role.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Set the role of the user.
     *
     * @param role The user's role to set.
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Get the user's password.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
/**
 * Set the user's password.
 *
 * @param password The password to set.

 */