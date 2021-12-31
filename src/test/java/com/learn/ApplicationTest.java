package com.learn;

import com.learn.config.AppConfig;
import com.learn.facade.BookingFacade;
import com.learn.model.Event;
import com.learn.model.Ticket;
import com.learn.model.User;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    @SneakyThrows
    void integrationTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookingFacade bookingFacade = context.getBean(BookingFacade.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        User user = bookingFacade.createUser("Enrique", "enr@test.com");
        Event event = bookingFacade.createEvent("Iron Maiden", dateFormat.parse("20/02/2021 18:00"));
        Ticket ticket = bookingFacade.bookTicket(user.getId(), event.getId(), 53, Ticket.Category.PREMIUM);

        assertAll(
                () -> assertTrue(bookingFacade.getBookedTickets(user).contains(ticket)),
                () -> assertTrue(bookingFacade.cancelTicket(ticket.getId())),
                () -> assertFalse(bookingFacade.getBookedTickets(user).contains(ticket)),
                () -> assertEquals(user, bookingFacade.getUserById(user.getId())),
                () -> assertEquals(event, bookingFacade.getEventById(event.getId()))
        );
    }
}
