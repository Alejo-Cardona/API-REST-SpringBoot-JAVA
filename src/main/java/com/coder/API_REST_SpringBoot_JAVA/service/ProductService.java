package com.coder.API_REST_SpringBoot_JAVA.service;

import com.coder.API_REST_SpringBoot_JAVA.models.Product;
import com.coder.API_REST_SpringBoot_JAVA.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findProduct(UUID id) {
        return productRepository.findById(id);
    }

    public boolean deleteProduct(UUID id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Product> updateProductStock(UUID productId, int quantity) {
        Optional<Product> existingProduct = productRepository.findById(productId);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            int newStock = product.getStock() - quantity;
            if (newStock < 0) {
                return Optional.empty();
            }
            product.setStock(newStock);
            productRepository.save(product);
            return Optional.of(product);
        } else {
            return Optional.empty();
        }
    }
}
