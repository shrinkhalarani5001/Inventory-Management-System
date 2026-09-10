package com.example.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "SKU is required")
    @Column(nullable = false, unique = true)
    private String sku;

    @NotBlank(message = "Category is required")
    @Column(nullable = false)
    private String category;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price cannot be negative")
    @Column(nullable = false)
    private Double price;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity cannot be negative")
    @Column(nullable = false)
    private Integer quantity;

    @NotNull(message = "Minimum stock is required")
    @PositiveOrZero(message = "Minimum stock cannot be negative")
    @Column(nullable = false)
    private Integer minStock;

    @Column(length = 500)
    private String description;

    public Product() {
    }

    public Product(String name, String sku, String category, Double price,
                   Integer quantity, Integer minStock, String description) {
        this.name = name;
        this.sku = sku;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.minStock = minStock;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Integer getMinStock() { return minStock; }
    public void setMinStock(Integer minStock) { this.minStock = minStock; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Transient
    public String getStatus() {
        if (quantity == null || quantity == 0) {
            return "OUT";
        }
        if (quantity <= minStock) {
            return "LOW";
        }
        return "IN";
    }

    @Transient
    public Double getStockValue() {
        return price * quantity;
    }
}
