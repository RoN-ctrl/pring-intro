package com.learn.facade.impl;

import com.learn.facade.BookingFacade;
import com.learn.model.Event;
import com.learn.model.Ticket;
import com.learn.model.User;
import com.learn.service.EventService;
import com.learn.service.TicketService;
import com.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class BookingFacadeImpl implements BookingFacade {
    private final UserService userService;
    private final TicketService ticketService;
    private final EventService eventService;

    @Autowired
    public BookingFacadeImpl(UserService userService, TicketService ticketService, EventService eventService) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.eventService = eventService;
    }

    @Override
    public Event getEventById(long eventId) {
        return eventService.getById(eventId);
    }

    @Override
    public List<Event> getEventsByTitle(String title) {
        return eventService.getByTitle(title);
    }

    @Override
    public List<Event> getEventsForDay(Date day) {
        return eventService.getByDay(day);
    }

    @Override
    public Event createEvent(String title, Date date) {
        return eventService.create(title, date);
    }

    @Override
    public Event updateEvent(long id, String title, Date date) {
        return eventService.update(id, title, date);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventService.deleteById(eventId);
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
    public List<User> getUsersByName(String name) {
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
        return ticketService.create(userId, eventId, category, place);
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        return ticketService.getByUser(user);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event) {
        return ticketService.getByEvent(event);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketService.delete(ticketId);
    }
}
