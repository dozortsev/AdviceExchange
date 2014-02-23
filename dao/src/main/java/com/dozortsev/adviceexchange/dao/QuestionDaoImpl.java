package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY, readOnly = true)
@SuppressWarnings("unchecked")
@Repository
public class QuestionDaoImpl extends GenericDaoImpl<Long, Question> implements QuestionDao {

    @Autowired private String findQuestionsByUserId;

    @Autowired private String findQuestionsByTagsId;

    public QuestionDaoImpl() {
        setEntityClass(Question.class);
    }

    @Override public List<Question> findQuestionsByUserId(Long userId) {

        return getCurrentSession().createSQLQuery(findQuestionsByUserId)
                .addEntity(getEntityClass()).setLong("userId", userId)
                .list();
    }

    @Override public List<Question> findQuestionsByTagId(Long... tagId) {

        return getCurrentSession().createSQLQuery(findQuestionsByTagsId)
                .addEntity(getEntityClass()).setParameterList("arrTagId", tagId)
                .list();
    }
}
