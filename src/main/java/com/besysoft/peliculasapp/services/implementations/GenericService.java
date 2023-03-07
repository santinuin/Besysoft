package com.besysoft.peliculasapp.services.implementations;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericService<E, D> {

    public List<D> list;

    public GenericService() {
        this.list = new ArrayList<>();
    }

    public E save(D d) {
        this.list.add(d);
        return (E) d;
    }

    public List<D> findAll() {
        return this.list;
    }

}
