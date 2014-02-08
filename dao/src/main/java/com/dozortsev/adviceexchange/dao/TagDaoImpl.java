package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Tag;
import org.springframework.stereotype.Repository;

@Repository
public class TagDaoImpl extends GenericDaoImpl<Long, Tag> implements TagDao {

    public TagDaoImpl() {
        this.entityClass = Tag.class;
    }
}
