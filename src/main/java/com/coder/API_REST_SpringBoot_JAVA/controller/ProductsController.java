package com.coder.API_REST_SpringBoot_JAVA.controller;

import com.coder.API_REST_SpringBoot_JAVA.models.Product;
import com.coder.API_REST_SpringBoot_JAVA.models.User;
import com.coder.API_REST_SpringBoot_JAVA.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductService productService;

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors().stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
        }
        try {
            Product createProduct = productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al crear el producto.");
        }
    }

    @PutMapping (value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateProduct(@PathVariable UUID id, @RequestBody Product product) {
        Optional<Product> existingProduct = productService.findProduct(id);

        if(!existingProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        Product updatedDataProduct = existingProduct.get();

        Optional.ofNullable(product.getTitle()).ifPresent(updatedDataProduct::setTitle);
        Optional.ofNullable(product.getDescription()).ifPresent(updatedDataProduct::setDescription);
        Optional.ofNullable(product.getPrice()).ifPresent(updatedDataProduct::setPrice);
        Optional.ofNullable(product.getCategory()).ifPresent(updatedDataProduct::setCategory);

        if(product.getStock() != 0) {
            updatedDataProduct.setStock(product.getStock());
        }

        Product saveProduct = productService.saveProduct(updatedDataProduct);
        return ResponseEntity.ok(saveProduct);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Product> findProduct(@PathVariable UUID id){
        return productService.findProduct(id);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        boolean isRemoved = productService.deleteProduct(id);

        if (!isRemoved) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
