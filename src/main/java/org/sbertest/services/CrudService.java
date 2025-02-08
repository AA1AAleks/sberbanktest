package org.sbertest.services;

import java.util.Collection;

public interface CrudService<T> {

    Collection<T> getAll();

    void create(T item);

    void update(Long id, T item);

    void toggle(Long id);
}
