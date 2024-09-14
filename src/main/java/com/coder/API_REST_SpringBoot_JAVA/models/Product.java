package com.coder.API_REST_SpringBoot_JAVA.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Title is mandatory")
    private String title;
    private String description;
    @NotNull(message = "Price cannot be null")
    private BigDecimal price;
    @Min(value = 1, message = "Stock must be 1 or greater")
    private int stock;
    @NotBlank(message = "Category is mandatory")
    private String category;

    // Constructor default JPA
    public Product(){
    }

    // Constructor
    public Product(String title, String description, BigDecimal price, int stock, String category) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product products = (Product) o;
        return stock == products.stock && Objects.equals(id, products.id) && Objects.equals(title, products.title) && Objects.equals(description, products.description) && Objects.equals(price, products.price) && Objects.equals(category, products.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, price, stock, category);
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", category='" + category + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
