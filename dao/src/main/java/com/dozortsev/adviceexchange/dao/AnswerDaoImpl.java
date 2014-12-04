package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.tables.TAnswer;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Answer;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.AnswerRecord;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.dozortsev.adviceexchange.domain.jooq.enums.UserActivityType.ANSWER;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY, readOnly = true)
@Repository
public class AnswerDaoImpl extends GenericDaoImpl<AnswerRecord, TAnswer> implements AnswerDao {

    public AnswerDaoImpl() {
        setTable(TAnswer.ANSWER);
        setIdField(TAnswer.ANSWER.ID);
    }

    @Override public List<Answer> findByQuestionId(int questionId) {

        final Field<BigDecimal> totalScore = DSL.sum(v.SCORE).as("totalScore");

        return dsl.select(a.ID, ua.TYPE, ua.CONTENT, ua.CREATED, ua.USER_ID, totalScore, a.ACCEPTED, a.QT_ID)
                .from(ua)
                    .leftOuterJoin(v).on(v.ACTIVITY_ID.eq(ua.ID))
                    .join(a).on(ua.ID.eq(a.ID))
                .where(a.QT_ID.eq(questionId))
                    .and(ua.TYPE.eq(ANSWER))
                    .and(ua.ACTIVE.isTrue())
                .groupBy(ua.ID)
                .orderBy(totalScore.desc())

                .fetchInto(Answer.class);
    }

    @Override public List<Answer> findByUserId(int userId) {

        return dsl.select(a.ID, ua.TYPE, ua.CONTENT, ua.CREATED, ua.USER_ID, ua.ACTIVE, a.ACCEPTED)
                .from(ua)
                    .join(a).on(ua.ID.eq(a.ID))
                .where(ua.USER_ID.eq(userId))
                    .and(ua.TYPE.eq(ANSWER))

                .fetchInto(Answer.class);
    }
}
