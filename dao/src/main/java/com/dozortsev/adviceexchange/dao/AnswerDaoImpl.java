package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Answer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class AnswerDaoImpl extends GenericDaoImpl<Long, Answer> implements AnswerDao {

    public AnswerDaoImpl() {
        setEntityClass(Answer.class);
    }
}
