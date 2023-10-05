package com.example.webshopbackend.dto;

public class OriginalDTO {

    private long id;
    private String title;
    private String size;
    private String material;
    private String description;
    private float price;
    private String url;
    private String category;
    private long quantity;



    public OriginalDTO() {
    }

    public OriginalDTO(long id, String title, String size, String material, String description, float price, String url, String category, long quantity) {
        this.id = id;
        this.title = title;
        this.size = size;
        this.material = material;
        this.description = description;
        this.price = price;
        this.url = url;
        this.category = category;
        this.quantity = quantity;
    }

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

    public float getPrice() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
