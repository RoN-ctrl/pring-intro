package com.learn.service.impl;

import com.learn.model.Event;
import com.learn.service.EventService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Override
    public Event createEvent(long id, String title, Date date) {
        return null;
    }

    @Override
    public Event getEvent(long id) {
        return null;
    }

    @Override
    public List<Event> getAllEvents() {
        return null;
    }

    @Override
    public Event updateEvent(long id, String title, Date date) {
        return null;
    }

    @Override
    public Event deleteEvent(long id) {
        return null;
    }
}
