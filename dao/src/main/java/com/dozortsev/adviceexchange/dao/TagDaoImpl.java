package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Tag;
import org.springframework.stereotype.Repository;

@Repository
public class TagDaoImpl extends GenericDaoImpl<Long, Tag> implements TagDao {

    protected TagDaoImpl() {
        super(Tag.class);
    }
}
