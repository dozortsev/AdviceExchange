package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.Tag;

public interface TagService extends GenericService<Long, Tag> {

    Tag findTagByName(String name);
}
