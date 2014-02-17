package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class QuestionDaoImpl extends GenericDaoImpl<Long, Question> implements QuestionDao {

    @Autowired
    private String findQuestionsByUserId;

    public QuestionDaoImpl() {
        setEntityClass(Question.class);
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override public Set<Question> findQuestionByUserId(Long userId) {
        Set<Question> questions = new HashSet<>();
        try {
            log.info(format("Find %s by User Id: %s", getEntityClass(), userId));
            questions.addAll(
                    getCurrentSession().createSQLQuery(findQuestionsByUserId)
                            .addEntity(getEntityClass()).setLong("userId", userId)
                            .list()
            );
            log.info(format("Set of %s have size: %s", getEntityClass(), questions.size()));
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return questions;
    }
}
