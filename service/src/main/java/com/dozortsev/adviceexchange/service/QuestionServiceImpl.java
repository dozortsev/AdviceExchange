package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.QuestionDao;
import com.dozortsev.adviceexchange.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Override public LinkedHashMap<Question, Integer> loadAll(Integer offset, Integer rowCount) {
        LinkedHashMap<Question, Integer> map = new LinkedHashMap<>();
        try {
            log.info(format("Load %ss", getEntityClass()));

            List<Integer> integers = getDao().answersCount(offset, rowCount);
            List<Question> questions = getDao().loadAll(offset, rowCount);

            int count = integers.size();
            if (count == questions.size()) {
                for (int i = 0; i < count; i++) {
                    map.put(questions.get(i), integers.get(i));
                }
                log.info(format("Load from %d to %d. Set size: %d", offset, rowCount, count));
            }
        } catch (Exception e) {
            log.error("Error: ", e);
        }
        return map;
    }

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
