package com.example.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.inventory.model.Product;
import com.example.inventory.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String dashboard(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String stock,
            Model model) {

        var products = service.search(keyword);

        if (stock != null && !stock.equalsIgnoreCase("all") && !stock.isBlank()) {
            products = products.stream()
                    .filter(p -> switch (stock.toLowerCase()) {
                        case "in" -> p.getStatus().equals("IN");
                        case "low" -> p.getStatus().equals("LOW");
                        case "out" -> p.getStatus().equals("OUT");
                        default -> true;
                    })
                    .toList();
        }

        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        model.addAttribute("stock", stock == null ? "all" : stock);

        addDashboardStats(model);

        return "index";
    }

    @GetMapping("/products/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle", "Add Product");
        return "product-form";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", service.getById(id));
        model.addAttribute("pageTitle", "Edit Product");
        return "product-form";
    }

    @PostMapping("/products/save")
    public String saveProduct(
            @Valid @ModelAttribute("product") Product product,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("pageTitle",
                    product.getId() == null ? "Add Product" : "Edit Product");
            return "product-form";
        }

        try {
            service.save(product);
        } catch (IllegalArgumentException e) {
            result.rejectValue("sku", "duplicate", e.getMessage());
            model.addAttribute("pageTitle",
                    product.getId() == null ? "Add Product" : "Edit Product");
            return "product-form";
        }

        return "redirect:/?success=Product+saved+successfully";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/?success=Product+deleted+successfully";
    }

    private void addDashboardStats(Model model) {
        model.addAttribute("totalProducts", service.totalProducts());
        model.addAttribute("totalUnits", service.totalUnits());
        model.addAttribute("lowStock", service.lowStockCount());
        model.addAttribute("inventoryValue", service.inventoryValue());
    }
}
