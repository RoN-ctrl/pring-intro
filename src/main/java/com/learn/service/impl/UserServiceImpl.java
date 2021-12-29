package com.learn.service.impl;

import com.learn.dao.impl.UserDao;
import com.learn.model.User;
import com.learn.model.impl.UserImpl;
import com.learn.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @SneakyThrows
    @Override
    public User createUser(String name, String email) {
        return userDao.save(new UserImpl(name, email));
    }

    @Override
    public User getUser(long id) {
        return userDao.get(id).orElseThrow();
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public User updateUser(long id, String name, String email) {
        User user = getUser(id);
        user.setName(name);
        user.setEmail(email);
        return userDao.update(user).orElseThrow();
    }

    @Override
    public User deleteUser(long id) {
        return userDao.delete(id).orElseThrow();
    }
}
