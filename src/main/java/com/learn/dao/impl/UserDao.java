package com.learn.dao.impl;

import com.learn.dao.Dao;
import com.learn.exceptions.IdAlreadyExistsException;
import com.learn.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDao implements Dao<User> {
    private Map<Long, User> users = new HashMap<>();

    @Override
    public User save(User user) throws IdAlreadyExistsException {
        if (users.containsKey(user.getId())) {
            throw new IdAlreadyExistsException("User with id=" + user.getId() + " already exists");
        }
        return users.put(user.getId(), user);
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.ofNullable(users.put(user.getId(), user));
    }

    @Override
    public Optional<User> delete(long id) {
        return Optional.ofNullable(users.remove(id));
    }
}
