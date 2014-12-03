package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.tables.TTag;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Tag;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.TagRecord;

import java.util.List;

public interface TagDao extends GenericDao<TagRecord, TTag> {

    List<Tag> findByName(String... names);

    List<Tag> loadAll();
}
