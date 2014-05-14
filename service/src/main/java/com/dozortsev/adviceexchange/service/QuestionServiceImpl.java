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

    private @Autowired QuestionDao questionDao;

    @Override public QuestionDao getDao() {
        return questionDao;
    }

    public QuestionServiceImpl() {
        setEntityClass(Question.class);
    }

    @Override public Integer addAnswer(Question question, Answer answer) {
        try {
            log.info(format("Add Answer to Question ID: %d", question.getId()));
            getDao().addAnswer(question, answer);
            log.info(format("Success create; Answer ID: %d", answer.getId()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return question.getAnswerCount();
    }

    @Override public Integer delAnswer(Answer answer) {
        Question question = answer.getQuestion();
        try {
            log.info(format("Delete Answer ID: %d form Question ID: %d", answer.getId(), question.getId()));
            getDao().delAnswer(question, answer);
            log.info("Success delete");

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return question.getAnswerCount();
    }

    @Override public Set<Question> findByKeyWords(String... keyWords) {
        Set<Question> questions = new LinkedHashSet<>();
        try {
            log.info(format("Find Questions by key words: %s", Arrays.toString(keyWords)));
            questions.addAll(getDao().findByKeyWord(keyWords));
            log.info(format("Set of Questions have size: %d", questions.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return questions;
    }

    @Transactional(readOnly = true)
    @Override public LinkedHashSet<Question> loadFrom(Integer offset) {
        LinkedHashSet<Question> questions = new LinkedHashSet<>();
        try {
            log.info(format("Load Questions from: %d; row count: 10", offset));
            questions.addAll(getDao().loadFrom(offset));
            log.info(format("Set of Questions have size: %d", questions.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return questions;
    }

    @Transactional(readOnly = true)
    @Override public Set<Question> findByUserId(Long userId) {
        Set<Question> questions = new LinkedHashSet<>();
        try {
            log.info(format("Find Questions by User ID: %d", userId));
            questions.addAll(getDao().findByUserId(userId));
            log.info(format("Set of Questions have size: %d", questions.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return questions;
    }

    @Transactional(readOnly = true)
    @Override public Set<Question> findByTags(String... tags) {
        Set<Question> questions = new LinkedHashSet<>();
        try {
            log.info(format("Find Questions by Tags: %s", Arrays.toString(tags)));
            questions.addAll(getDao().findByTags(tags));
            log.info(format("Set of Questions have size: %d", questions.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return questions;
    }
}
