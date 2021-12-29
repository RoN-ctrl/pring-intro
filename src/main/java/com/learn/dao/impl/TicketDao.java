package com.learn.dao.impl;

import com.learn.dao.Dao;
import com.learn.exceptions.IdAlreadyExistsException;
import com.learn.model.Ticket;
import com.learn.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

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
    public Optional<Ticket> get(long id) {
        return Optional.ofNullable(tickets.get(id));
    }

    @Override
    public List<Ticket> getAll() {
        return new ArrayList<>(tickets.values());
    }

    @Override
    public Optional<Ticket> update(Ticket ticket) {
        return Optional.ofNullable(tickets.put(ticket.getId(), ticket));
    }

    @Override
    public Optional<Ticket> delete(long id) {
        return Optional.ofNullable(tickets.remove(id));
    }
}
