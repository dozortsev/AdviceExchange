package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.GenericDao;
import com.dozortsev.adviceexchange.domain.AbstractEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW, rollbackFor = Exception.class)
@Service
public abstract class GenericServiceImpl<ID extends Serializable, T extends AbstractEntity<ID>> implements GenericService<ID, T> {

    private Class<T> entityClass;

    public abstract GenericDao<ID, T> getDao();

    @Override public ID create(T entity) {
        return getDao().create(entity);
    }

    @Override public void delete(T entity) {
        getDao().delete(entity);
    }

    @Override public void deleteById(ID id) {
        getDao().deleteById(id);
    }

    @Override public T findById(ID id) {
        return getDao().findById(id);
    }

    @Override public T update(T entity) {
        return getDao().update(entity);
    }
}
