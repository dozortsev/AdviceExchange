package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class AnswerDaoImpl extends GenericDaoImpl<Long, Answer> implements AnswerDao {

    @Autowired
    private String findAnswersByUserId;

    public AnswerDaoImpl() {
        setEntityClass(Answer.class);
    }

    @SuppressWarnings("unchecked")
    @Override public List<Answer> findAnswersByUserId(Long userId) {

        return getCurrentSession().createSQLQuery(findAnswersByUserId)
                .addEntity(getEntityClass()).setLong("userId", userId)
                .list();
    }
}
