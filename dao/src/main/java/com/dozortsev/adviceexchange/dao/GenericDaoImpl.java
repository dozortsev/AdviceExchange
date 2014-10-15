package com.dozortsev.adviceexchange.dao;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.UpdatableRecord;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public abstract class GenericDaoImpl<R extends Record, T extends TableImpl<R>> implements GenericDao<R, T> {

    private Field<Integer> idField;

    private TableImpl<R> table;

    @Autowired DSLContext dsl;

    @Override public int create(UpdatableRecord record) {
        return Objects.requireNonNull(record).store();
    }

    @Override public void delete(UpdatableRecord record) {
        dsl.executeDelete(record);
    }

    @Transactional(readOnly = true)
    @Override public R findById(int id) {
        return dsl.fetchOne(table, table.field(idField).equal(id));
    }

    @Override public void update(UpdatableRecord record) {
        Objects.requireNonNull(record).store();
    }

    @Transactional(readOnly = true)
    @Override public int totalCount() {
        return dsl.select(DSL.count(idField)).from(table).fetchOne(0, int.class);
    }

    TableImpl<R> getTable() {
        return table;
    }

    void setTable(TableImpl<R> table) {
        this.table = table;
    }

    Field<Integer> getIdField() {
        return idField;
    }

    void setIdField(Field<Integer> idField) {
        this.idField = idField;
    }
}