package com.ticketnerd.ticketservice.repository;

import com.ticketnerd.ticketservice.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {
}
