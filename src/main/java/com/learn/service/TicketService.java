package com.learn.service;

import com.learn.model.Event;
import com.learn.model.Ticket;
import com.learn.model.User;

import java.util.List;

import static com.learn.model.Ticket.Category;

public interface TicketService {

    Ticket create(long userId, long eventId, Category category, int place);

    List<Ticket> getByUser(User user);

    List<Ticket> getByEvent(Event event);

    boolean delete(long id);
}
