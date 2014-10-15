package com.dozortsev.adviceexchange.dao;

import org.jooq.Record;
import org.jooq.UpdatableRecord;
import org.jooq.impl.TableImpl;

public interface GenericDao<R extends Record, T extends TableImpl<R>> {

    int create(UpdatableRecord record);

    void delete(UpdatableRecord record);

    R findById(int id);

    void update(UpdatableRecord record);

    int totalCount();
}
