package com.learn.service.impl;

import com.learn.dao.impl.TicketDao;
import com.learn.model.Event;
import com.learn.model.Ticket;
import com.learn.model.User;
import com.learn.model.impl.TicketImpl;
import com.learn.service.TicketService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketDao ticketDao;

    @SneakyThrows
    @Override
    public Ticket create(long userId, long eventId, Ticket.Category category, int place) {
        return ticketDao.save(new TicketImpl(eventId, userId, category, place));
    }

    @Override
    public List<Ticket> getByUser(User user) {
        return ticketDao.getByUser(user);
    }

    @Override
    public List<Ticket> getByEvent(Event event) {
        return ticketDao.getByEvent(event);
    }

    @Override
    public boolean delete(long id) {
        return ticketDao.delete(id);
    }
}
