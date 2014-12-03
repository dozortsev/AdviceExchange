package com.dozortsev.adviceexchange.service;

import org.jooq.UpdatableRecord;

import java.io.Serializable;

public interface GenericService<E extends Serializable> {

    int create(UpdatableRecord record);

    void delete(UpdatableRecord record);

    E findById(int id);

    void update(UpdatableRecord record);

    int totalCount();
}
