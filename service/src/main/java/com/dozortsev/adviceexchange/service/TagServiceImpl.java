package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.TagDao;
import com.dozortsev.adviceexchange.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW)
@Service
public class TagServiceImpl extends GenericServiceImpl<Long, Tag> implements TagService {

    private @Autowired TagDao tagDao;

    @Override public TagDao getDao() {
        return tagDao;
    }

    public TagServiceImpl() {
        setEntityClass(Tag.class);
    }

    @Transactional(readOnly = true)
    @Override public Tag findTagByName(String name) {
        try {
            log.info(format("Find Tab by name: %s", name));
            Tag tag = getDao().findTagByName(name);

            if (tag != null) {
                log.info("Successful found");
                return tag;
            }
            log.info(format("Tag not exist"));
        } catch (Exception e) {
            log.error("Error: " + e);
        }
        return null;
    }
}
