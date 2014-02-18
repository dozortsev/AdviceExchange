package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class QuestionDaoImpl extends GenericDaoImpl<Long, Question> implements QuestionDao {

    @Autowired
    private String findQuestionsByUserId;

    public QuestionDaoImpl() {
        setEntityClass(Question.class);
    }

    @SuppressWarnings("unchecked")
    @Override public List<Question> findQuestionByUserId(Long userId) {

        return getCurrentSession().createSQLQuery(findQuestionsByUserId)
                .addEntity(getEntityClass()).setLong("userId", userId)
                .list();
    }
}
