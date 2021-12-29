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
    public User create(String name, String email) {
        return userDao.save(new UserImpl(name, email));
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id).orElseThrow();
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email).orElseThrow();
    }

    @Override
    public List<User> getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public User update(long id, String name, String email) {
        User user = getById(id);
        user.setName(name);
        user.setEmail(email);
        return userDao.update(user).orElseThrow();
    }

    @Override
    public boolean deleteById(long id) {
        return userDao.delete(id);
    }
}
