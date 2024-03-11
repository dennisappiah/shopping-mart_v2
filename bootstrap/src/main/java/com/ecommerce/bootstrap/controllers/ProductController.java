package com.ecommerce.bootstrap.controllers;

import com.ecommerce.bootstrap.model.persistence.entities.Product;
import com.ecommerce.bootstrap.model.persistence.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductRepository _productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(_productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.of(_productRepository.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Product>> getItemsByName(@PathVariable String name) {
        List<Product> products = _productRepository.findByName(name);
        return products == null || products.isEmpty() ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(products);

    }
}
