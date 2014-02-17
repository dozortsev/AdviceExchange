package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class AnswerDaoImpl extends GenericDaoImpl<Long, Answer> implements AnswerDao {

    @Autowired
    private String findAnswersByUserId;

    public AnswerDaoImpl() {
        setEntityClass(Answer.class);
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override public Set<Answer> findAnswersByUserId(Long userId) {
        Set<Answer> answers = new HashSet<>();
        try {
            log.info(format("Find %s by User Id: %s", getEntityClass(), userId));
            answers.addAll(
                    getCurrentSession().createSQLQuery(findAnswersByUserId)
                            .addEntity(getEntityClass()).setLong("userId", userId)
                            .list()
            );
            log.info(format("Set of %s have size: %s", getEntityClass(), answers.size()));
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return answers;
    }
}
