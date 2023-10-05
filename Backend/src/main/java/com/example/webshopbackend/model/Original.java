package com.example.webshopbackend.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Original {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String title;
    @NotNull
    private String size;
    @NotNull
    private String material;
    @NotNull
    private String description;
    private float price;
    @NotNull
    private String url;
    @Enumerated(EnumType.STRING)
    private Category category;

    private long quantity;

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

}

