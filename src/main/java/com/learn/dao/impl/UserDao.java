package com.learn.dao.impl;

import com.learn.dao.Dao;
import com.learn.exceptions.IdAlreadyExistsException;
import com.learn.model.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserDao implements Dao<User> {
    private Map<Long, User> users = new HashMap<>();

    @Override
    public User save(User user) throws IdAlreadyExistsException {
        if (users.containsKey(user.getId())) {
            throw new IdAlreadyExistsException("User with id=" + user.getId() + " already exists");
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> getById(long id) {
        return Optional.ofNullable(users.get(id));
    }

    public Optional<User> getByEmail(String email) {
        return users.values().stream()
                .filter(u -> Objects.equals(email, u.getEmail()))
                .findFirst();
    }

    public List<User> getByName(String name) {
        return users.values().stream()
                .filter(u -> Objects.equals(name, u.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.ofNullable(users.replace(user.getId(), user));
    }

    @Override
    public boolean delete(long id) {
        users.remove(id);
        return !users.containsKey(id);
    }
}
