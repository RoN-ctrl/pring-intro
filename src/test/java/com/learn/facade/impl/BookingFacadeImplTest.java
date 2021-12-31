package com.learn.facade.impl;

import com.learn.config.AppConfig;
import com.learn.model.Event;
import com.learn.model.Ticket;
import com.learn.model.User;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class BookingFacadeImplTest {

    private static final String EVENT_TITLE = "Giselle";
    private static final String EVENT_DATE = "20/02/2021 18:00";
    private static final String USER_NAME = "Jack";
    private static final String USER_EMAIL = "jack@test.com";
    private static final int PLACE = 17;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Autowired
    private BookingFacadeImpl bookingFacade;

    @Test
    void getEventByIdTest() {
        Event event = createTestEvent();
        Event eventById = bookingFacade.getEventById(event.getId());

        assertNotNull(eventById);
        assertEquals(event, eventById);
    }

    @Test
    void getEventsByTitleTest() {
        Event event = createTestEvent();
        List<Event> eventsByTitle = bookingFacade.getEventsByTitle(event.getTitle());

        assertNotNull(eventsByTitle);
        assertTrue(eventsByTitle.contains(event));
    }

    @Test
    void getEventsForDayTest() throws ParseException {
        Event event = createTestEvent();
        List<Event> eventsByDay = bookingFacade.getEventsForDay(DATE_FORMAT.parse(EVENT_DATE));

        assertNotNull(eventsByDay);
        assertTrue(eventsByDay.contains(event));
    }

    @Test
    void createEventTest() {
        Event event = createTestEvent();

        assertNotNull(event);
        assertAll(
                () -> assertEquals(EVENT_TITLE, event.getTitle()),
                () -> assertEquals(EVENT_DATE, DATE_FORMAT.format(event.getDate()))
        );
    }

    @Test
    void updateEventTest() throws ParseException {
        Event event = createTestEvent();
        String updatedTitle = "Updated title";
        String updatedDate = "10/02/2022 10:00";
        Event updatedEvent =
                bookingFacade.updateEvent(event.getId(), updatedTitle, DATE_FORMAT.parse(updatedDate));

        assertNotNull(updatedEvent);
        assertAll(
                () -> assertEquals(updatedTitle, updatedEvent.getTitle()),
                () -> assertEquals(updatedDate, DATE_FORMAT.format(updatedEvent.getDate()))
        );
    }

    @Test
    void deleteEventTest() {
        Event event = createTestEvent();

        assertTrue(bookingFacade.deleteEvent(event.getId()));
        assertFalse(bookingFacade.getEventsByTitle(event.getTitle()).contains(event));
    }

    @Test
    void getUserByIdTest() {
        User user = createTestUser();
        User userById = bookingFacade.getUserById(user.getId());

        assertNotNull(userById);
        assertEquals(user, userById);
    }

    @Test
    void getUserByEmailTest() {
        User user = createTestUser();
        User userByEmail = bookingFacade.getUserByEmail(user.getEmail());

        assertNotNull(userByEmail);
        assertEquals(user, userByEmail);
    }

    @Test
    void getUsersByNameTest() {
        User user = createTestUser();
        List<User> usersByName = bookingFacade.getUsersByName(user.getName());

        assertNotNull(usersByName);
        assertTrue(usersByName.contains(user));
    }

    @Test
    void createUserTest() {
        User user = createTestUser();

        assertNotNull(user);
        assertAll(
                () -> assertEquals(USER_NAME, user.getName()),
                () -> assertEquals(USER_EMAIL, user.getEmail())
        );
    }

    @Test
    void updateUserTest() {
        User user = createTestUser();
        String updatedName = "Updated Jack";
        String updatedEmail = "jach@updated.com";
        User updatedUser = bookingFacade.updateUser(user.getId(), updatedName, updatedEmail);

        assertNotNull(updatedUser);
        assertAll(
                () -> assertEquals(updatedName, updatedUser.getName()),
                () -> assertEquals(updatedEmail, updatedUser.getEmail())
        );
    }

    @Test
    void deleteUserTest() {
        User user = createTestUser();

        assertTrue(bookingFacade.deleteUser(user.getId()));
        assertFalse(bookingFacade.getUsersByName(user.getName()).contains(user));
    }

    @Test
    void bookTicketTest() {
        User user = createTestUser();
        Event event = createTestEvent();

        Ticket ticket = bookingFacade.bookTicket(user.getId(), event.getId(), PLACE, Ticket.Category.STANDARD);

        assertNotNull(ticket);
        assertAll(
                () -> assertEquals(user.getId(), ticket.getUserId()),
                () -> assertEquals(event.getId(), ticket.getEventId()),
                () -> assertEquals(PLACE, ticket.getPlace()),
                () -> assertEquals(Ticket.Category.STANDARD, ticket.getCategory())
        );
    }

    @Test
    void getBookedTicketsByUserTest() {
        User user = createTestUser();
        Event event = createTestEvent();
        Ticket ticket = bookingFacade.bookTicket(user.getId(), event.getId(), PLACE, Ticket.Category.STANDARD);

        List<Ticket> bookedByUser = bookingFacade.getBookedTickets(user);

        assertNotNull(bookedByUser);
        assertTrue(bookedByUser.contains(ticket));
    }

    @Test
    void GetBookedTicketsByEventTest() {
        User user = createTestUser();
        Event event = createTestEvent();
        Ticket ticket = bookingFacade.bookTicket(user.getId(), event.getId(), PLACE, Ticket.Category.STANDARD);

        List<Ticket> bookedByEvent = bookingFacade.getBookedTickets(event);

        assertNotNull(bookedByEvent);
        assertTrue(bookedByEvent.contains(ticket));
    }

    @Test
    void cancelTicketTest() {
        User user = createTestUser();
        Event event = createTestEvent();
        Ticket ticket = bookingFacade.bookTicket(user.getId(), event.getId(), PLACE, Ticket.Category.STANDARD);

        assertTrue(bookingFacade.cancelTicket(ticket.getId()));
        assertFalse(bookingFacade.getBookedTickets(user).contains(ticket));
    }

    @SneakyThrows
    private Event createTestEvent() {
        return bookingFacade.createEvent(EVENT_TITLE, DATE_FORMAT.parse(EVENT_DATE));
    }

    private User createTestUser() {
        return bookingFacade.createUser(USER_NAME, USER_EMAIL);
    }
}