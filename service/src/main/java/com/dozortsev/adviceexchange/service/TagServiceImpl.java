package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.TagDao;
import com.dozortsev.adviceexchange.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW)
@Service
public class TagServiceImpl extends GenericServiceImpl<Long, Tag> implements TagService {

    @Autowired private TagDao tagDao;

    @Override public TagDao getDao() {
        return tagDao;
    }
}
