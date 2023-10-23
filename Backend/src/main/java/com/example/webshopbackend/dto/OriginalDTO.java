/**
 * OriginalDTO Class
 *
 * This class represents a Data Transfer Object (DTO) for managing original products. It includes information about the
 * original product's attributes, such as its unique identifier, title, size, material, description, price, URL,
 * category, quantity, and associated image.
 */
package com.example.webshopbackend.dto;

public class OriginalDTO {

    /**
     * The unique identifier of the original product.
     */
    private long id;

    /**
     * The title of the original product.
     */
    private String title;

    /**
     * The size of the original product.
     */
    private String size;

    /**
     * The material of the original product.
     */
    private String material;

    /**
     * The description of the original product.
     */
    private String description;

    /**
     * The price of the original product.
     */
    private float price;

    /**
     * The URL of the original product.
     */
    private String url;

    /**
     * The category of the original product.
     */
    private String category;

    /**
     * The quantity of the original product available.
     */
    private long quantity;

    /**
     * The unique identifier of the associated image.
     */
    private long imageId;

    /**
     * Constructs an empty OriginalDTO.
     */
    public OriginalDTO() {}

    /**
     * Constructs an OriginalDTO with specified attributes.
     *
     * @param id The unique identifier of the original product.
     * @param title The title of the original product.
     * @param size The size of the original product.
     * @param material The material of the original product.
     * @param description The description of the original product.
     * @param price The price of the original product.
     * @param url The URL of the original product.
     * @param category The category of the original product.
     * @param quantity The quantity of the original product available.
     * @param imageId The unique identifier of the associated image.
     */
    public OriginalDTO(long id, String title, String size, String material, String description, float price, String url, String category, long quantity, long imageId) {
        this.id = id;
        this.title = title;
        this.size = size;
        this.material = material;
        this.description = description;
        this.price = price;
        this.url = url;
        this.category = category;
        this.quantity = quantity;
        this.imageId = imageId;
    }

    /**
     * Get the unique identifier of the original product.
     *
     * @return The unique identifier.
     */
    public long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the original product.
     *
     * @param id The unique identifier to set.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the title of the original product.
     *
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the original product.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the size of the original product.
     *
     * @return The size.
     */
    public String getSize() {
        return size;
    }

    /**
     * Set the size of the original product.
     *
     * @param size The size to set.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Get the material of the original product.
     *
     * @return The material.
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Set the material of the original product.
     *
     * @param material The material to set.
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * Get the description of the original product.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the original product.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the price of the original product.
     *
     * @return The price.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Set the price of the original product.
     *
     * @param price The price to set.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Get the URL of the original product.
     *
     * @return The URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the URL of the original product.
     *
     * @param url The URL to set.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get the category of the original product.
     *
     * @return The category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set the category of the original product.
     *
     * @param category The category to set.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Get the quantity of the original product available.
     *
     * @return The quantity.
     */
    public long getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity of the original product available.
     *
     * @param quantity The quantity to set.
     */
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    /**
     * Get the unique identifier of the associated image.
     *
     * @return The image's unique identifier.
     */
    public long getImageId() {
        return imageId;
    }

    /**
     * Set the unique identifier of the associated image.
     *
     * @param imageId The image's unique identifier to set.
     */
    public void setImageId(long imageId) {
        this.imageId = imageId;
    }
}
