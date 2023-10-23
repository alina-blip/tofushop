/**
 * Image Class
 *
 * This class represents an Image entity used in the web application. It is mapped to a corresponding database table.
 */
package com.example.webshopbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String path;

    /**
     * Get the ID of the Image.
     *
     * @return The unique identifier for the image in the database.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID of the Image.
     *
     * @param id The unique identifier for the image in the database.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the file path of the Image.
     *
     * @return The path to the image file, which can be used for retrieval.
     */
    public String getPath() {
        return path;
    }

    /**
     * Set the file path of the Image.
     *
     * @param path The path to the image file, which is used to locate and serve the image.
     */
    public void setPath(String path) {
        this.path = path;
    }
}
