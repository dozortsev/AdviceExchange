package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.Tag;

import java.util.List;
import java.util.Set;

public interface TagService extends GenericService<Long, Tag> {

    List<Tag> findByName(String... names);

    Set<Tag> loadAll();
}
