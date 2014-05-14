package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.TagDao;
import com.dozortsev.adviceexchange.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    @Override public Set<Tag> loadAll() {
        Set<Tag> tags = new LinkedHashSet<>();
        try {
            log.info("Load all Tags");
            tags.addAll(getDao().loadAll());
            log.info(format("Set of Tags have size: %d", tags.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return tags;
    }

    @Transactional(readOnly = true)
    @Override public List<Tag> findByName(String... names) {
        List<Tag> tags = new ArrayList<>();
        try {
            log.info(format("Find Tabs by names: %s", Arrays.toString(names)));
            tags.addAll(getDao().findByName(names));
            log.info(format("Set of Tags have size: %d", tags.size()));
        } catch (Exception e) {
            log.error("Error: " + e);
        }
        return tags;
    }
}
