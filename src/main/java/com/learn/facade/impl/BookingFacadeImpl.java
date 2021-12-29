package com.learn.facade.impl;

import com.learn.facade.BookingFacade;
import com.learn.model.Event;
import com.learn.model.Ticket;
import com.learn.model.User;
import com.learn.service.EventService;
import com.learn.service.TicketService;
import com.learn.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class BookingFacadeImpl implements BookingFacade {
    private UserService userService;
    private TicketService ticketService;
    private EventService eventService;

    public BookingFacadeImpl(UserService userService, TicketService ticketService, EventService eventService) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.eventService = eventService;
    }

    @Override
    public Event getEventById(long eventId) {
        return null;
    }

    @Override
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public Event createEvent(Event event) {
        return null;
    }

    @Override
    public Event updateEvent(Event event) {
        return null;
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return false;
    }

    @Override
    public User getUserById(long userId) {
        return userService.getById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userService.getByEmail(email);
    }

    @Override
    //TODO add pagination
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userService.getByName(name);
    }

    @Override
    public User createUser(String name, String email) {
        return userService.create(name, email);
    }

    @Override
    public User updateUser(long id, String name, String email) {
        return userService.update(id, name, email);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userService.deleteById(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        return null;
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return false;
    }
}
