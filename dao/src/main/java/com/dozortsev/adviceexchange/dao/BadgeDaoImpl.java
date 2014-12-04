package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.tables.TBadge;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Badge;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.BadgeRecord;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY, readOnly = true)
@Repository
public class BadgeDaoImpl extends GenericDaoImpl<BadgeRecord, TBadge> implements BadgeDao {

    public BadgeDaoImpl() {
        setTable(b);
        setIdField(b.ID);
    }

    @Override public List<Badge> findByUserId(int userId) {

        return dsl.select(b.fields())
                .from(b)
                    .join(ub).on(ub.BADGE_ID.eq(b.ID))
                    .join(u).on(ub.USER_ID.eq(u.ID))
                .where(u.ID.eq(userId))

                .fetchInto(Badge.class);
    }
}
