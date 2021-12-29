package com.learn.service;

import com.learn.model.Event;

import java.util.Date;
import java.util.List;

public interface EventService {

    Event createEvent(long id, String title, Date date);

    Event getEvent(long id);

    List<Event> getAllEvents();

    Event updateEvent(long id, String title, Date date);

    Event deleteEvent(long id);
}
