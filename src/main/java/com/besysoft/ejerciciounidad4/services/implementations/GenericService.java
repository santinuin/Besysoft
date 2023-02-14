package com.besysoft.ejerciciounidad4.services.implementations;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericService<E> {

    public List<E> list;

    public GenericService() {
        this.list = new ArrayList<>();
    }

    public E update(E e) {
        this.list.add(e);
        return e;
    }

    public List<E> findAll() {
        return this.list;
    }

}
