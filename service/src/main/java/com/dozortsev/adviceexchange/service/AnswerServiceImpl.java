package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.AnswerDao;
import com.dozortsev.adviceexchange.domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW, readOnly = true)
@Service
public class AnswerServiceImpl extends GenericServiceImpl<Long, Answer> implements AnswerService {

    @Autowired private AnswerDao answerDao;

    @Override public AnswerDao getDao() {
        return answerDao;
    }

    public AnswerServiceImpl() {
        setEntityClass(Answer.class);
    }

    @Override public Set<Answer> findAnswersByUserId(Long userId) {
        Set<Answer> answers = new HashSet<>();
        try {
            log.info(format("Find %s by User Id: %s", getEntityClass(), userId));
            answers.addAll(getDao().findAnswersByUserId(userId));
            log.info(format("Set of %s have size: %s", getEntityClass(), answers.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return answers;
    }

    @Override public Set<Answer> findAnswersByQuestionId(Long questionId) {
        HashSet<Answer> answers = new HashSet<>();
        try {
            log.info(format("Find %s by Question Id: %s", getEntityClass(), questionId));
            answers.addAll(getDao().findAnswersByQuestionId(questionId));
            log.info(format("Set of %s have size: %s", getEntityClass(), answers.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return answers;
    }
}
