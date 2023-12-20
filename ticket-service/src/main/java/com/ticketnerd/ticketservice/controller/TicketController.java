package com.ticketnerd.ticketservice.controller;

import com.ticketnerd.ticketservice.dto.TicketRequest;
import com.ticketnerd.ticketservice.dto.TicketResponse;
import com.ticketnerd.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTicket(@RequestBody TicketRequest ticketRequest) {
        ticketService.createTicket(ticketRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TicketResponse> getAllTickets() {
        return ticketService.getAllTickets();
    }
}
