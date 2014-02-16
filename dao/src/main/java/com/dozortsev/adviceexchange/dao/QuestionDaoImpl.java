package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class QuestionDaoImpl extends GenericDaoImpl<Long, Question> implements QuestionDao {

    private String findQuestionsByUserId;

    @Autowired
    @Override public void setQuery(String findQuestionsByUserId) {
        this.findQuestionsByUserId = findQuestionsByUserId;
    }

    @Override public String getQuery() {
        return findQuestionsByUserId;
    }

    public QuestionDaoImpl() {
        setEntityClass(Question.class);
    }
}
