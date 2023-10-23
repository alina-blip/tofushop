package com.example.webshopbackend.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Original Class
 *
 * This class represents an original product available in the webshop. It defines the attributes and properties of an original product, including its title, size, material, description, price, URL, category, quantity, and associated image ID.
 */
@Entity
public class Original {

    @Id
    @GeneratedValue
    private long id; // Unique identifier for the original product

    @NotNull
    private String title; // The title or name of the original product

    @NotNull
    private String size; // The size or dimensions of the original product

    @NotNull
    private String material; // The material used for the original product

    @NotNull
    private String description; // A detailed description of the original product

    private float price; // The price of the original product

    @NotNull
    private String url; // The URL or path to view the original product

    @Enumerated(EnumType.STRING)
    private Category category; // The category of the product (e.g., ORIGINAL, PRINT, STICKER)

    private long quantity; // The available quantity of the original product

    private long imageId; // The ID of the associated image for the original product

    /////////////////////////////////////////////////////////
    // Getters and setters for the above attributes
    /////////////////////////////////////////////////////////

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }
}

