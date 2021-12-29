package com.learn.dao;

import com.learn.exceptions.IdAlreadyExistsException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    T save(T t) throws IdAlreadyExistsException;

    Optional<T> get(long id);

    List<T> getAll();

    Optional<T> update(T t);

    Optional<T> delete(long id);
}
