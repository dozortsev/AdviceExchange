package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.QuestionDao;
import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW)
@Service
public class QuestionServiceImpl extends GenericServiceImpl<Long, Question> implements QuestionService {

    @Autowired private QuestionDao questionDao;

    @Override public QuestionDao getDao() {
        return questionDao;
    }

    public QuestionServiceImpl() {
        setEntityClass(Question.class);
    }

    @Override public Integer addAnswer(Question question, Answer answer) {
        try {
            log.info(format("Add answer to question; ID: %d", question.getId()));
            getDao().addAnswer(question, answer);
            log.info(format("Success create; ID: %d", answer.getId()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return question.getAnswerCount();
    }

    @Override public Integer delAnswer(Question question, Answer answer) {
        try {
            log.info(format("Delete answer; ID: %d form question; ID: %d", answer.getId(), question.getId()));
            getDao().delAnswer(question, answer);
            log.info("Success delete");

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return question.getAnswerCount();
    }

    @Transactional(readOnly = true)
    @Override public LinkedHashSet<Question> loadAll(Integer offset) {
        LinkedHashSet<Question> questions = new LinkedHashSet<>();
        try {
            log.info(format("Load %ss", getEntityClass()));

            questions.addAll(getDao().loadAll(offset));

            log.info(format("Load from %d size 2. Set size: %d", offset, questions.size()));
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return questions;
    }

    @Transactional(readOnly = true)
    @Override public Set<Question> findQuestionsByUserId(Long userId) {
        Set<Question> questions = new HashSet<>();
        try {
            log.info(format("Find %s by User Id: %d", getEntityClass(), userId));
            questions.addAll(getDao().findQuestionsByUserId(userId));
            log.info(format("Set of %s have size: %d", getEntityClass(), questions.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return questions;
    }

    @Transactional(readOnly = true)
    @Override public Set<Question> findQuestionsByTags(String... tags) {
        Set<Question> questions = new LinkedHashSet<>();
        try {
            log.info(format("Find %s by Tags: %s", getEntityClass(), Arrays.toString(tags)));
            questions.addAll(getDao().findQuestionsByTags(tags));
            log.info(format("Set of %s have size: %d", getEntityClass(), questions.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return questions;
    }
}
