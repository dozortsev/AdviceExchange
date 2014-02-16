package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class AnswerDaoImpl extends GenericDaoImpl<Long, Answer> implements AnswerDao {

    private String findAnswersByUserId;

    @Autowired
    @Override public void setQuery(String findAnswersByUserId) {
        this.findAnswersByUserId = findAnswersByUserId;
    }

    @Override public String getQuery() {
        return findAnswersByUserId;
    }

    public AnswerDaoImpl() {
        setEntityClass(Answer.class);
    }
}
