package com.learn.dao.impl;

import com.learn.dao.Dao;
import com.learn.exceptions.IdAlreadyExistsException;
import com.learn.model.Event;
import com.learn.model.impl.EventImpl;
import com.learn.util.Utils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class EventDao implements Dao<Event> {
    private final Map<Long, Event> events = new HashMap<>();

    @Value("${p.events}")
    String filePath;

    @PostConstruct
    @SneakyThrows
    public void populateEvents() {
        List<String> eventsFromFile = Utils.readLines(filePath);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        int indexForTitle = "title".length() + 1;
        int indexForDate = "date".length() + 1;

        for (String event : eventsFromFile) {
            String[] split = event.split(",");
            if (split.length == 2) {
                String date = split[1].substring(indexForDate);
                save(new EventImpl(split[0].substring(indexForTitle), dateFormat.parse(date)));
            }
        }
    }

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
