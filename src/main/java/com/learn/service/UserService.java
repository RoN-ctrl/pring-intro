package com.learn.service;

import com.learn.model.User;

import java.util.List;

public interface UserService {

    User createUser(String name, String email);

    User getUser(long id);

    List<User> getAllUsers();

    User updateUser(long id, String name, String email);

    User deleteUser(long id);
}
