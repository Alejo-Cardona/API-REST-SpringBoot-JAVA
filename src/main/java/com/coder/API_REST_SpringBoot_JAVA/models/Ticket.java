package com.coder.API_REST_SpringBoot_JAVA.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TICKETS")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDateTime date;
    private BigDecimal total_price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "ticket")
    private List<TicketProduct> ticketProducts;

    // Constructor default JPA
    public Ticket() {
    }

    // Constructor
    public Ticket(LocalDateTime date, BigDecimal total_price, User user) {
        this.date = date;
        this.total_price = total_price;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(date, ticket.date) && Objects.equals(total_price, ticket.total_price) && Objects.equals(user, ticket.user) && Objects.equals(ticketProducts, ticket.ticketProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, total_price, user, ticketProducts);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", date=" + date +
                ", total_price=" + total_price +
                ", user=" + user +
                ", ticketProducts=" + ticketProducts +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<TicketProduct> getTicketProducts() {
        return ticketProducts;
    }

    public void setTicketProducts(List<TicketProduct> ticketProducts) {
        this.ticketProducts = ticketProducts;
    }
}
