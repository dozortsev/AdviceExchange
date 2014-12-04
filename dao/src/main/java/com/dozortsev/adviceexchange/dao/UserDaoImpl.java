package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.enums.UserActivityType;
import com.dozortsev.adviceexchange.domain.jooq.tables.TUser;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.User;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.UserActivity;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.UserRecord;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY, readOnly = true)
@Repository
public class UserDaoImpl extends GenericDaoImpl<UserRecord, TUser> implements UserDao {

    public UserDaoImpl() {
        setTable(TUser.USER);
        setIdField(TUser.USER.ID);
    }

    @Override public int totalCount(String name) {

        return dsl.select(DSL.count(u.ID))
                .from(u)
                .where(u.NAME.likeIgnoreCase(name))
                .and(u.ENABLED.isTrue())

                .fetchOne(0, int.class);
    }

    @Override public List<User> findByName(String name, int offset) {

        return dsl.select(u.ID, u.NAME, u.EMAIL, u.JOINED)
                .from(u)
                .where(u.NAME.likeIgnoreCase(name))
                .limit(offset, 36)
                .fetchInto(User.class);
    }

    @Override public User findByLogin(String login) {

        return dsl.fetchOne(u, u.EMAIL.eq(login).and(u.ENABLED.isTrue())).into(User.class);
    }

    @Override public List<UserActivity> userActivities(int id) {

        return dsl.select(ua.ID, ua.TYPE, q.TITLE, a.ACCEPTED, a.QT_ID)
                .from(ua)

                .leftOuterJoin(a)
                    .on(ua.USER_ID.eq(a.ID)).and(ua.TYPE.eq(UserActivityType.ANSWER))

                .leftOuterJoin(c)
                    .on(ua.USER_ID.eq(c.ID)).and(ua.TYPE.eq(UserActivityType.COMMENT))

                .leftOuterJoin(q)
                    .on(ua.USER_ID.eq(q.ID)).and(ua.TYPE.eq(UserActivityType.QUESTION))

                .where(ua.USER_ID.eq(id))

                .fetchInto(UserActivity.class);
    }
}
