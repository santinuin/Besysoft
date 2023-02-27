package com.besysoft.ejerciciounidad5.services.implementations;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericService<E> {

    public List<E> list;

    public GenericService() {
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
