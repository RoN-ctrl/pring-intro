package com.learn.dao;

import com.learn.exception.IdAlreadyExistsException;

import java.util.Optional;

public interface Dao<T> {

    T save(T t) throws IdAlreadyExistsException;

    Optional<T> getById(long id);

    Optional<T> update(T t);

    boolean delete(long id);
}
