package com.example.inventory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.inventory.model.Product;
import com.example.inventory.repository.ProductRepository;

@SpringBootApplication
public class InventoryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner loadSampleData(ProductRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Product(
                        "Wireless Mouse", "WM-001", "Electronics",
                        799.00, 24, 5, "Ergonomic wireless mouse"));

                repository.save(new Product(
                        "Mechanical Keyboard", "KB-002", "Electronics",
                        2499.00, 8, 10, "RGB mechanical keyboard"));

                repository.save(new Product(
                        "Office Chair", "OC-003", "Furniture",
                        7499.00, 0, 3, "Ergonomic office chair"));

                repository.save(new Product(
                        "Notebook", "NB-004", "Stationery",
                        120.00, 50, 10, "A5 ruled notebook"));
            }
        };
    }
}
