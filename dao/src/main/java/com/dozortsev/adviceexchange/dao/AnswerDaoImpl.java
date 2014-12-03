package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.enums.UserActivityType;
import com.dozortsev.adviceexchange.domain.jooq.tables.TAnswer;
import com.dozortsev.adviceexchange.domain.jooq.tables.TUserActivity;
import com.dozortsev.adviceexchange.domain.jooq.tables.TVote;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Answer;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.AnswerRecord;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.dozortsev.adviceexchange.domain.jooq.tables.TAnswer.ANSWER;
import static com.dozortsev.adviceexchange.domain.jooq.tables.TUserActivity.USER_ACTIVITY;
import static com.dozortsev.adviceexchange.domain.jooq.tables.TVote.VOTE;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY, readOnly = true)
@Repository
public class AnswerDaoImpl extends GenericDaoImpl<AnswerRecord, TAnswer> implements AnswerDao {

    public AnswerDaoImpl() {
        setIdField(ANSWER.ID);
        setTable(ANSWER);
    }

    @Override public List<Answer> findByQuestionId(int questionId) {
        TVote v = VOTE.as("v");
        TAnswer a = ANSWER.as("a");
        TUserActivity ua = USER_ACTIVITY.as("ua");

        final Field<BigDecimal> totalScore = DSL.sum(v.SCORE).as("totalScore");

        return dsl.select(a.ID, ua.TYPE, ua.CONTENT, ua.CREATED, ua.USER_ID, totalScore, a.ACCEPTED, a.QT_ID)
                .from(ua)

                .leftOuterJoin(v).on(v.ACTIVITY_ID.eq(ua.ID))

                .join(a).on(ua.ID.eq(a.ID))

                .where(a.QT_ID.eq(questionId))

                    .and(ua.TYPE.eq(UserActivityType.ANSWER))
                    .and(ua.ACTIVE.isTrue())

                .groupBy(ua.ID)
                .orderBy(totalScore.desc())

                .fetchInto(Answer.class);
    }

    @Override public List<Answer> findByUserId(int userId) {
        TAnswer a = ANSWER.as("a");
        TUserActivity ua = USER_ACTIVITY.as("ua");

        return dsl.select(a.ID, ua.TYPE, ua.CONTENT, ua.CREATED, ua.USER_ID, ua.ACTIVE, a.ACCEPTED)
                .from(ua)
                .join(a)
                    .on(ua.ID.eq(a.ID))
                .where(ua.USER_ID.eq(userId))
                    .and(ua.TYPE.eq(UserActivityType.ANSWER))

                .fetchInto(Answer.class);
    }
}
