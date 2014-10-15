package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.enums.UserActivityType;
import com.dozortsev.adviceexchange.domain.jooq.tables.*;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.UserActivityRecord;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.UserRecord;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.dozortsev.adviceexchange.domain.jooq.tables.Answer.ANSWER;
import static com.dozortsev.adviceexchange.domain.jooq.tables.Comment.COMMENT;
import static com.dozortsev.adviceexchange.domain.jooq.tables.Question.QUESTION;
import static com.dozortsev.adviceexchange.domain.jooq.tables.User.USER;
import static com.dozortsev.adviceexchange.domain.jooq.tables.UserActivity.USER_ACTIVITY;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY, readOnly = true)
@Repository
public class UserDaoImpl extends GenericDaoImpl<UserRecord, User> implements UserDao {

    public UserDaoImpl() {
        setIdField(USER.ID);
        setTable(USER);
    }

    @Override public int totalCount(String name) {
        return dsl.select(DSL.count(USER.ID))
                .from(USER)
                .where(USER.NAME.likeIgnoreCase(name))
                .and(USER.ENABLED.isTrue())
                .fetchOne(0, int.class);
    }

    @Override public List<UserRecord> findByName(String name, int offset) {

        return dsl.select(USER.ID, USER.NAME, USER.EMAIL, USER.JOINED)
                .from(USER)
                .where(USER.NAME.likeIgnoreCase(name))
                .limit(offset, 36)
                .fetchInto(USER);
    }

    @Override public UserRecord findByLogin(String login) {
        return dsl.fetchOne(USER, USER.EMAIL.eq(login).and(USER.ENABLED.isTrue()));
    }

    @Override public List<UserActivityRecord> userActivities(int id) {

        UserActivity ua = USER_ACTIVITY.as("ua");
        Question q = QUESTION.as("q");
        Answer a = ANSWER.as("a");
        Comment c = COMMENT.as("c");

        return dsl.select(ua.ID, ua.TYPE, q.TITLE, q.ASW_COUNT, a.ACCEPTED, a.QT_ID)
                .from(ua)

                .leftOuterJoin(a)
                    .on(ua.USER_ID.eq(a.ID)).and(ua.TYPE.eq(UserActivityType.ANSWER))

                .leftOuterJoin(c)
                    .on(ua.USER_ID.eq(c.ID)).and(ua.TYPE.eq(UserActivityType.COMMENT))

                .leftOuterJoin(q)
                    .on(ua.USER_ID.eq(q.ID)).and(ua.TYPE.eq(UserActivityType.QUESTION))

                .where(ua.USER_ID.eq(id))
                .fetchInto(UserActivityRecord.class);
    }
}
