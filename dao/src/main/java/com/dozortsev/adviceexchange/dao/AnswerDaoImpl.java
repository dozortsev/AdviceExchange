package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Answer;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerDaoImpl extends GenericDaoImpl<Long, Answer> implements AnswerDao {

    protected AnswerDaoImpl() {
        super(Answer.class);
    }
}
