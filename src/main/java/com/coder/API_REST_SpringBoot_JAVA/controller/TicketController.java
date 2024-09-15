package com.coder.API_REST_SpringBoot_JAVA.controller;

import com.coder.API_REST_SpringBoot_JAVA.models.Product;
import com.coder.API_REST_SpringBoot_JAVA.models.Ticket;
import com.coder.API_REST_SpringBoot_JAVA.models.TicketProduct;
import com.coder.API_REST_SpringBoot_JAVA.models.User;
import com.coder.API_REST_SpringBoot_JAVA.service.ProductService;
import com.coder.API_REST_SpringBoot_JAVA.service.TicketService;
import com.coder.API_REST_SpringBoot_JAVA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    private TicketProduct ticketProduct;
    private UserService userService;
    private ProductService productService;

    @PostMapping(value = "/create/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTicket(@PathVariable UUID id, @RequestBody List<TicketProduct> ticketProducts) {
        // Verifica la existencia del usuario
        Optional<User> existingUser = userService.findUser(id);
        if (!existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        }
        User user = existingUser.get();

        // Verifica la existencia de cada producto y calcula el total_price
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<TicketProduct> validTicketProducts = new ArrayList<>();

        for (TicketProduct ticketProduct : ticketProducts) {
            Optional<Product> existingProduct = productService.findProduct(ticketProduct.getProduct().getId());
            if (!existingProduct.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto con ID " + ticketProduct.getProduct().getId() + " no encontrado.");
            }
            Product product = existingProduct.get();

            ticketProduct.setProduct(product);
            ticketProduct.setTicket(null);

            totalPrice = totalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(ticketProduct.getQuantity())));

            // Actualiza el stock del producto
            int newStock = product.getStock() - ticketProduct.getQuantity();
            if (newStock < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock insuficiente para el producto con ID " + product.getId());
            }
            productService.updateProductStock(product.getId(), newStock);

            validTicketProducts.add(ticketProduct);

            validTicketProducts.add(ticketProduct);
        }

        // Crea el Ticket
        LocalDateTime currentDate = LocalDateTime.now();
        Ticket ticket = new Ticket(currentDate, totalPrice, user);
        Ticket createdTicket = ticketService.saveTicket(ticket);

        // Asocia el Ticket con cada TicketProduct y guarda los TicketProduct
        for (TicketProduct ticketProduct : validTicketProducts) {
            ticketProduct.setTicket(createdTicket);
        }
        List<TicketProduct> savedTicketProducts = ticketService.saveTicketProducts(validTicketProducts);

        // Actualiza el Ticket con los TicketProducts guardados
        createdTicket.setTicketProducts(savedTicketProducts);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTicket);
    }
}
