package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Tag;

import java.util.List;
import java.util.Set;

public interface TagService extends GenericService<Tag> {

    List<Tag> findByName(String... names);

    Set<Tag> loadAll();
}
