package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.tables.TBadge;
import com.dozortsev.adviceexchange.domain.jooq.tables.TUser;
import com.dozortsev.adviceexchange.domain.jooq.tables.TUserBadge;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Badge;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.BadgeRecord;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.dozortsev.adviceexchange.domain.jooq.tables.TBadge.BADGE;
import static com.dozortsev.adviceexchange.domain.jooq.tables.TUser.USER;
import static com.dozortsev.adviceexchange.domain.jooq.tables.TUserBadge.USER_BADGE;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY, readOnly = true)
@Repository
public class BadgeDaoImpl extends GenericDaoImpl<BadgeRecord, TBadge> implements BadgeDao {

    public BadgeDaoImpl() {
        setTable(BADGE);
        setIdField(BADGE.ID);
    }

    @Override public List<Badge> findByUserId(int userId) {
        TUser u = USER.as("u");
        TBadge bdg = BADGE.as("bdg");
        TUserBadge ub = USER_BADGE.as("ub");

        return dsl.select(bdg.fields())

                .from(bdg)

                .join(ub).on(ub.BADGE_ID.eq(bdg.ID))

                .join(u).on(ub.USER_ID.eq(u.ID))

                .where(u.ID.eq(userId))

                .fetchInto(Badge.class);
    }
}
