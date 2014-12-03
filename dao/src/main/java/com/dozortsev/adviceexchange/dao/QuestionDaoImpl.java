package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.enums.UserActivityType;
import com.dozortsev.adviceexchange.domain.jooq.tables.TQuestion;
import com.dozortsev.adviceexchange.domain.jooq.tables.TQuestionTag;
import com.dozortsev.adviceexchange.domain.jooq.tables.TTag;
import com.dozortsev.adviceexchange.domain.jooq.tables.TUserActivity;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Question;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.QuestionRecord;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.dozortsev.adviceexchange.domain.jooq.tables.TQuestion.QUESTION;
import static com.dozortsev.adviceexchange.domain.jooq.tables.TQuestionTag.QUESTION_TAG;
import static com.dozortsev.adviceexchange.domain.jooq.tables.TTag.TAG;
import static com.dozortsev.adviceexchange.domain.jooq.tables.TUserActivity.USER_ACTIVITY;
import static java.lang.String.join;
import static org.jooq.impl.DSL.concat;
import static org.jooq.impl.DSL.count;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class QuestionDaoImpl extends GenericDaoImpl<QuestionRecord, TQuestion> implements QuestionDao {

    public QuestionDaoImpl() {
        setTable(QUESTION);
        setIdField(QUESTION.ID);
    }

    @Override public List<Question> loadFrom(int offset) {
        TQuestion q = QUESTION.as("q");
        TUserActivity ua = USER_ACTIVITY.as("ua");

        return dsl.select(ua.ID, ua.TYPE, ua.USER_ID, q.TITLE, ua.CONTENT, ua.CREATED)
                .from(q)
                .join(ua).on(ua.ID.eq(q.ID))
                .where(ua.TYPE.eq(UserActivityType.QUESTION))
                    .and(ua.ACTIVE.isTrue())
                .orderBy(ua.CREATED.desc())
                .limit(offset, 10)

                .fetchInto(Question.class);
    }

    @Override public List<Question> findByKeyWord(String... keyWords) {
        TQuestion q = QUESTION.as("q");
        TUserActivity ua = USER_ACTIVITY.as("ua");

        return dsl.select(ua.ID, ua.TYPE, ua.USER_ID, q.TITLE, ua.CONTENT, ua.CREATED)
                .from(q)
                .join(ua).on(ua.ID.eq(q.ID))
                .where(ua.TYPE.eq(UserActivityType.QUESTION))
                    .and(ua.ACTIVE.isTrue())
                    .and(concat(q.TITLE, ua.CONTENT).likeRegex(join("|", keyWords)))
                .orderBy(ua.CREATED.desc())

                .fetchInto(Question.class);
    }

    @Override public List<Question> findByUserId(int userId) {
        TQuestion q = QUESTION.as("q");
        TUserActivity ua = USER_ACTIVITY.as("ua");

        return dsl.select(ua.ID, ua.TYPE, ua.USER_ID, q.TITLE, ua.CONTENT, ua.CREATED)
                .from(q)
                .join(ua).on(ua.ID.eq(q.ID))
                .where(ua.TYPE.eq(UserActivityType.QUESTION))
                    .and(ua.ACTIVE.isTrue())
                    .and(ua.USER_ID.eq(userId))
                .orderBy(ua.CREATED.desc())

                .fetchInto(Question.class);
    }

    @Override public List<Question> findByTags(String... tags) {
        TTag t = TAG.as("t");
        TQuestion q = QUESTION.as("q");
        TQuestionTag qt = QUESTION_TAG.as("qt");
        TUserActivity ua = USER_ACTIVITY.as("ua");

        return dsl.select(ua.ID, ua.TYPE, ua.USER_ID, q.TITLE, ua.CONTENT, ua.CREATED)

                .from(q)
                    .join(ua).on(ua.ID.eq(q.ID))
                    .join(qt).on(qt.QT_ID.eq(q.ID))
                    .join(t).on(qt.TAG_ID.eq(t.ID))

                .where(t.NAME.in(tags)).and(ua.ACTIVE.isTrue())

                .groupBy(qt.QT_ID)

                .having(count(t.ID).greaterOrEqual(tags.length))

                .orderBy(ua.CREATED.desc())

                .fetchInto(Question.class);
    }
}