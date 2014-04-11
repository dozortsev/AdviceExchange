package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@SuppressWarnings("unchecked")
@Repository
public class QuestionDaoImpl extends GenericDaoImpl<Long, Question> implements QuestionDao {

    @Autowired private String findQuestionsByUserId;

    @Autowired private String findQuestionsByTags;

    @Autowired private String loadQuestionsSet;

    public QuestionDaoImpl() {
        setEntityClass(Question.class);
    }

    @Override public List<Question> loadAll(Integer offset) {

        return getCurrentSession().createSQLQuery(loadQuestionsSet)
                .addEntity(getEntityClass())
                .setInteger("offset", offset)
                .list();
    }

    @Override public List<Question> findQuestionsByUserId(Long userId) {

        return getCurrentSession().createSQLQuery(findQuestionsByUserId)
                .addEntity(getEntityClass())
                .setLong("userId", userId)
                .list();
    }

    @Override public List<Question> findQuestionsByTags(String... tags) {

        return getCurrentSession().createSQLQuery(findQuestionsByTags)
                .addEntity(getEntityClass())
                .setParameterList("tags", tags)
                .setParameter("tagCount", tags.length)
                .list();
    }
}
