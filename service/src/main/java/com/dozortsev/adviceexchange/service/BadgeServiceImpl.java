package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.BadgeDao;
import com.dozortsev.adviceexchange.domain.Badge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW)
@Service
public class BadgeServiceImpl extends GenericServiceImpl<Long, Badge> implements BadgeService {

    @Autowired private BadgeDao badgeDao;

    @Override public BadgeDao getDao() {
        return badgeDao;
    }
}
