package com.learn.service.impl;

import com.learn.model.Ticket;
import com.learn.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public Ticket createTicket(long id, long eventId, long userId, Ticket.Category category, int place) {
        return null;
    }

    @Override
    public Ticket getTicket(long id) {
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return null;
    }

    @Override
    public Ticket updateTicket(long id, long eventId, long userId, Ticket.Category category, int place) {
        return null;
    }

    @Override
    public Ticket deleteTicket(long id) {
        return null;
    }
}
