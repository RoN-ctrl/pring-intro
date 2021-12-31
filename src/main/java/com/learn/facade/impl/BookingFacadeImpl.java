package com.learn.facade.impl;

import com.learn.aspect.Loggable;
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
    @Loggable
    public Event getEventById(long eventId) {
        return eventService.getById(eventId);
    }

    @Override
    @Loggable
    public List<Event> getEventsByTitle(String title) {
        return eventService.getByTitle(title);
    }

    @Override
    @Loggable
    public List<Event> getEventsForDay(Date day) {
        return eventService.getByDay(day);
    }

    @Override
    @Loggable
    public Event createEvent(String title, Date date) {
        return eventService.create(title, date);
    }

    @Override
    @Loggable
    public Event updateEvent(long id, String title, Date date) {
        return eventService.update(id, title, date);
    }

    @Override
    @Loggable
    public boolean deleteEvent(long eventId) {
        return eventService.deleteById(eventId);
    }

    @Override
    @Loggable
    public User getUserById(long userId) {
        return userService.getById(userId);
    }

    @Override
    @Loggable
    public User getUserByEmail(String email) {
        return userService.getByEmail(email);
    }

    @Override
    @Loggable
    public List<User> getUsersByName(String name) {
        return userService.getByName(name);
    }

    @Override
    @Loggable
    public User createUser(String name, String email) {
        return userService.create(name, email);
    }

    @Override
    @Loggable
    public User updateUser(long id, String name, String email) {
        return userService.update(id, name, email);
    }

    @Override
    @Loggable
    public boolean deleteUser(long userId) {
        return userService.deleteById(userId);
    }

    @Override
    @Loggable
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        return ticketService.create(userId, eventId, category, place);
    }

    @Override
    @Loggable
    public List<Ticket> getBookedTickets(User user) {
        return ticketService.getByUser(user);
    }

    @Override
    @Loggable
    public List<Ticket> getBookedTickets(Event event) {
        return ticketService.getByEvent(event);
    }

    @Override
    @Loggable
    public boolean cancelTicket(long ticketId) {
        return ticketService.delete(ticketId);
    }
}
