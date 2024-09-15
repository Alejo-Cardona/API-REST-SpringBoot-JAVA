package com.coder.API_REST_SpringBoot_JAVA.models;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class TicketProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;


    // Constructor Default JPA
    public TicketProduct(){
    }

    public TicketProduct(UUID id, Ticket ticket, Product product, int quantity) {
        this.id = id;
        this.ticket = ticket;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketProduct that = (TicketProduct) o;
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(ticket, that.ticket) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticket, product, quantity);
    }

    @Override
    public String toString() {
        return "TicketProduct{" +
                "id=" + id +
                ", ticket=" + ticket +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
