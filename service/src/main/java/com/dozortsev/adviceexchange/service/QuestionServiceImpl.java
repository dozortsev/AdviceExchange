package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.QuestionDao;
import com.dozortsev.adviceexchange.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW, readOnly = true)
@Service
public class QuestionServiceImpl extends GenericServiceImpl<Long, Question> implements QuestionService {

    @Autowired private QuestionDao questionDao;

    @Override public QuestionDao getDao() {
        return questionDao;
    }

    public QuestionServiceImpl() {
        setEntityClass(Question.class);
    }

    @Override public Set<Question> findQuestionsByUserId(Long userId) {
        Set<Question> questions = new HashSet<>();
        try {
            log.info(format("Find %s by User Id: %s", getEntityClass(), userId));
            questions.addAll(getDao().findQuestionsByUserId(userId));
            log.info(format("Set of %s have size: %s", getEntityClass(), questions.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return questions;
    }

    @Override public Set<Question> findQuestionsByTagsId(Long... tagsId) {
        Set<Question> questions = new HashSet<>();
        try {
            log.info(format("Find %s by Tags Id: %s", getEntityClass(), Arrays.toString(tagsId)));
            questions.addAll(getDao().findQuestionsByTagId(tagsId));
            log.info(format("Set of %s have size: %s", getEntityClass(), questions.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return questions;
    }
}
