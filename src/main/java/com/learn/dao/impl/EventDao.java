package com.learn.dao.impl;

import com.learn.dao.Dao;
import com.learn.exceptions.IdAlreadyExistsException;
import com.learn.model.Event;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class EventDao implements Dao<Event> {
    private Map<Long, Event> events = new HashMap<>();

    @Override
    public Event save(Event event) throws IdAlreadyExistsException {
        if (events.containsKey(event.getId())) {
            throw new IdAlreadyExistsException("Event with id=" + event.getId() + " already exists");
        }
        events.put(event.getId(), event);
        return event;
    }

    @Override
    public Optional<Event> getById(long id) {
        return Optional.ofNullable(events.get(id));
    }

    public List<Event> getByTitle(String title) {
        return events.values().stream()
                .filter(e -> Objects.equals(title, e.getTitle()))
                .collect(Collectors.toList());
    }

    public List<Event> getByDay(Date day) {
        return events.values().stream()
                .filter(e -> Objects.equals(day, e.getDate()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Event> update(Event event) {
        return Optional.ofNullable(events.replace(event.getId(), event));
    }

    @Override
    public boolean delete(long id) {
        events.remove(id);
        return !events.containsKey(id);
    }
}
