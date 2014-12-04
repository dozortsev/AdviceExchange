package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.tables.TTag;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Tag;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.TagRecord;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class TagDaoImpl extends GenericDaoImpl<TagRecord, TTag> implements TagDao {

    public TagDaoImpl() {
        setTable(t);
        setIdField(t.ID);
    }

    @Override public List<Tag> loadAll() {
        return dsl.fetch(t).into(Tag.class);
    }

    @Override public List<Tag> findByName(String... names) {
        return dsl.fetch(t, t.NAME.in(names)).into(Tag.class);
    }
}
