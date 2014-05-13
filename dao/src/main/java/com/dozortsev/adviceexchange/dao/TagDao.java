package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Tag;

import java.util.List;

public interface TagDao extends GenericDao<Long, Tag> {

    List<Tag> findTagByName(String... names);

    List<Tag> loadAll();
}
