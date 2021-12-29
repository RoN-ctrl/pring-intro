package com.learn.service;

import com.learn.model.Ticket;

import java.util.List;

import static com.learn.model.Ticket.Category;

public interface TicketService {

    Ticket createTicket(long id, long eventId, long userId, Category category, int place);

    Ticket getTicket(long id);

    List<Ticket> getAllTickets();

    Ticket updateTicket(long id, long eventId, long userId, Category category, int place);

    Ticket deleteTicket(long id);
}
