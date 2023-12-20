package com.ticketnerd.ticketservice.service;


import com.ticketnerd.ticketservice.dto.TicketRequest;
import com.ticketnerd.ticketservice.dto.TicketResponse;
import com.ticketnerd.ticketservice.model.Ticket;
import com.ticketnerd.ticketservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketService {

    private final TicketRepository ticketRepository;

    public void createTicket(TicketRequest ticketRequest) {
        Ticket ticket = Ticket.builder()
                .name(ticketRequest.getName())
                .description(ticketRequest.getDescription())
                .price(ticketRequest.getPrice())
                .build();

        ticketRepository.save(ticket);
        log.info("Ticket {} is saved", ticket.getId());
    }

    public List<TicketResponse> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(this::mapToTicketResponse).toList();
    }

    private TicketResponse mapToTicketResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .name(ticket.getName())
                .description(ticket.getDescription())
                .price(ticket.getPrice())
                .build();
    }
}
