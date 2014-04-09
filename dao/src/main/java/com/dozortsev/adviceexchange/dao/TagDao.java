package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Tag;

public interface TagDao extends GenericDao<Long, Tag> {

    Tag findTagByName(String name);
}
