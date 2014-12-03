package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.GenericDao;
import org.apache.log4j.Logger;
import org.jooq.UpdatableRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import static java.lang.String.format;
import static java.lang.System.nanoTime;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW, rollbackFor = Exception.class)
@Service
public abstract class GenericServiceImpl<E extends Serializable> implements GenericService<E> {

    private Class<E> entityClass;

    final Logger log = Logger.getLogger(this.getClass().getName());

    protected abstract GenericDao getDao();

    @Override public int create(UpdatableRecord record) {
        final long start = nanoTime();
        try {
            log.info(format("Create new %s", getEntityClass().getSimpleName()));
            getDao().create(record);
//            log.info(format("Success create; ID: %s", record.getId()));
        }
        catch (Exception e) {
            log.error("Error: ", e);
        }
        finally {
            log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        }
        return record.getValue(0, int.class);
    }

    @Override public void delete(UpdatableRecord record) {
        final long start = nanoTime();
        try {
            log.info(format("Delete %s", getEntityClass().getSimpleName()));
            getDao().delete(record);
            log.info("Success delete");
        }
        catch (Exception e) {
            log.error("Error: ", e);
        }
        finally {
            log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        }
    }

    @Override public E findById(int id) {
        final long start = nanoTime();
        try {
            log.info(format("Find %s by ID: %s", getEntityClass().getSimpleName(), id));
            E entity = getDao().findById(id).into(getEntityClass());

            if (entity != null) {
                log.info("Success found");
                return entity;
            }
            log.info(format("%s with this ID not exist", getEntityClass().getSimpleName()));
        }
        catch (Exception e) {
            log.error("Error: ", e);
        }
        finally {
            log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        }
        return null;
    }

    @Override public void update(UpdatableRecord record) {
        final long start = nanoTime();
        try {
            log.info(format("Update %s. ID: %s", getEntityClass().getSimpleName(), record.getValue(0, int.class)));
            getDao().update(record);
            log.info("Success updated");
        }
        catch (Exception e) {
            log.error("Error: ", e);
        }
        finally {
            log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        }
    }

    @Override public int totalCount() {
        final long start = nanoTime();
        int totalCount = 0;
        try {
            totalCount = getDao().totalCount();
            log.info(format("Total count of %ss is: %d", getEntityClass().getSimpleName(), totalCount));
        }
        catch (Exception e) {
            log.error("Error: ", e);
        }
        finally {
            log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        }
        return totalCount;
    }

    public Class<E> getEntityClass() {
        return entityClass;
    }
    public void setEntityClass(Class<E> entityClass) {
        this.entityClass = entityClass;
    }
}
