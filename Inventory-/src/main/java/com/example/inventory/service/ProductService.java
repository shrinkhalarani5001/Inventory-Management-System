package com.example.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.inventory.model.Product;
import com.example.inventory.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public List<Product> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllProducts();
        }
        return repository
                .findByNameContainingIgnoreCaseOrSkuContainingIgnoreCaseOrCategoryContainingIgnoreCase(
                        keyword.trim(), keyword.trim(), keyword.trim());
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }

    @Transactional
    public Product save(Product product) {
        String sku = product.getSku().trim().toUpperCase();
        product.setSku(sku);

        repository.findBySkuIgnoreCase(sku).ifPresent(existing -> {
            if (product.getId() == null || !existing.getId().equals(product.getId())) {
                throw new IllegalArgumentException("SKU already exists: " + sku);
            }
        });

        return repository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public long totalProducts() {
        return repository.count();
    }

    public long totalUnits() {
        return repository.findAll().stream()
                .mapToLong(p -> p.getQuantity())
                .sum();
    }

    public long lowStockCount() {
        return repository.findAll().stream()
                .filter(p -> "LOW".equals(p.getStatus()))
                .count();
    }

    public double inventoryValue() {
        return repository.findAll().stream()
                .mapToDouble(Product::getStockValue)
                .sum();
    }
}
