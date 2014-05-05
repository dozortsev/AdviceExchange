package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@SuppressWarnings("unchecked")
@Repository
public class QuestionDaoImpl extends GenericDaoImpl<Long, Question> implements QuestionDao {

    private @Autowired String findQuestionsByUserId;

    private @Autowired String findQuestionsByTags;

    private @Autowired String loadQuestionsSet;

    private @Autowired String findQuestionsByKeyWords;

    public QuestionDaoImpl() {
        setEntityClass(Question.class);
    }

    @Override public Integer addAnswer(Question question, Answer answer) {

        question.setAnswerCount(question.getAnswerCount() + 1);
        getCurrentSession().save(answer);
        getCurrentSession().update(question);

        return question.getAnswerCount();
    }

    @Override public Integer delAnswer(Question question, Answer answer) {

        question.setAnswerCount(question.getAnswerCount() - 1);
        getCurrentSession().delete(answer);
        getCurrentSession().update(question);

        return question.getAnswerCount();
    }

    @Override public List<Question> loadFrom(Integer offset) {

        return getCurrentSession().createSQLQuery(loadQuestionsSet)
                .addEntity(getEntityClass())
                .setInteger("offset", offset)
                .list();
    }

    @Override public List<Question> findByKeyWord(String... keyWords) {

        StringBuilder queryStr = new StringBuilder(findQuestionsByKeyWords)
                .append("HAVING CONCAT(qs_title, ' ', ua_content) REGEXP :word0").append("\n");

        for (int i = 1; i < keyWords.length; i++)
            queryStr.append("AND CONCAT(qs_title, ' ', ua_content) REGEXP :word")
                    .append(i).append("\n");

        queryStr.append("ORDER BY ua_created DESC");    // building of the query is finished

        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(queryStr.toString()).addEntity(getEntityClass());

        for (int i = 0; i < keyWords.length; i++)
            sqlQuery.setParameter("word" + i, keyWords[i]);

        return sqlQuery.list();
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
