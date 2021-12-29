package com.learn.dao.impl;

import com.learn.dao.Dao;
import com.learn.exceptions.IdAlreadyExistsException;
import com.learn.model.Event;
import com.learn.model.Ticket;
import com.learn.model.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TicketDao implements Dao<Ticket> {
    private Map<Long, Ticket> tickets = new HashMap<>();

    @Override
    public Ticket save(Ticket ticket) throws IdAlreadyExistsException {
        if (tickets.containsKey(ticket.getId())) {
            throw new IdAlreadyExistsException("Ticket with id=" + ticket.getId() + " already exists");
        }
        return tickets.put(ticket.getId(), ticket);
    }

    @Override
    public Optional<Ticket> getById(long id) {
        return Optional.ofNullable(tickets.get(id));
    }

    public List<Ticket> getByUser(User user) {
        return tickets.values().stream()
                .filter(t -> Objects.equals(user.getId(), t.getUserId()))
                .collect(Collectors.toList());
    }

    public List<Ticket> getByEvent(Event event) {
        return tickets.values().stream()
                .filter(t -> Objects.equals(event.getId(), t.getEventId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Ticket> update(Ticket ticket) {
        return Optional.ofNullable(tickets.replace(ticket.getId(), ticket));
    }

    @Override
    public boolean delete(long id) {
        tickets.remove(id);
        return !tickets.containsKey(id);
    }
}
