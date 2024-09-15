package com.coder.API_REST_SpringBoot_JAVA.service;

import com.coder.API_REST_SpringBoot_JAVA.models.Product;
import com.coder.API_REST_SpringBoot_JAVA.models.Ticket;
import com.coder.API_REST_SpringBoot_JAVA.models.TicketProduct;
import com.coder.API_REST_SpringBoot_JAVA.repository.TicketProductRepository;
import com.coder.API_REST_SpringBoot_JAVA.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    private TicketProductRepository ticketProductRepository;
    private TicketProduct ticketProduct;

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public TicketProduct saveTicketProduct(TicketProduct ticketProduct) {
        return ticketProductRepository.save(ticketProduct);
    }

    public List<TicketProduct> saveTicketProducts(List<TicketProduct> ticketProducts) {
        return ticketProductRepository.saveAll(ticketProducts);
    }
}
