package com.besysoft.peliculasapp.repositories.memory;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericRepository<E> {

    public List<E> list;

    public GenericRepository() {
        this.list = new ArrayList<>();
    }

    public E save(E e) {
        this.list.add(e);
        return e;
    }

    public List<E> findAll() {
        return this.list;
    }

}
