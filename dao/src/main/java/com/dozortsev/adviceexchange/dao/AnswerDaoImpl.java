package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY, readOnly = true)
@SuppressWarnings("unchecked")
@Repository
public class AnswerDaoImpl extends GenericDaoImpl<Long, Answer> implements AnswerDao {

    @Autowired private String findAnswersByUserId;

    @Autowired private String findAnswersByQuestionId;

    public AnswerDaoImpl() {
        setEntityClass(Answer.class);
    }

    @Override public List<Answer> findByUserId(Long userId) {

        return getCurrentSession().createSQLQuery(findAnswersByUserId)
                .addEntity(getEntityClass())
                .setLong("userId", userId)
                .list();
    }

    @Override public List<Answer> findByQuestionId(Long questionId) {

        return getCurrentSession().createSQLQuery(findAnswersByQuestionId)
                .addEntity(getEntityClass())
                .setLong("questionId", questionId)
                .list();
    }
}
