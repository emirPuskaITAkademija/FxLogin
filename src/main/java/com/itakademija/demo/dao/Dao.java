package com.itakademija.demo.dao;

import java.util.List;

public interface Dao<E> {
    E save(E e);

    void update(E e);

    void delete(E e);

    E findById(int id);

    List<E> findAll();
}