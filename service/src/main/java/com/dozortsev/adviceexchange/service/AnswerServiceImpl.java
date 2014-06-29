package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.AnswerDao;
import com.dozortsev.adviceexchange.domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.lang.String.format;
import static java.lang.System.nanoTime;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW, readOnly = true)
@Service
public class AnswerServiceImpl extends GenericServiceImpl<Long, Answer> implements AnswerService {

    private @Autowired AnswerDao answerDao;

    @Override public AnswerDao getDao() {
        return answerDao;
    }

    public AnswerServiceImpl() {
        setEntityClass(Answer.class);
    }

    @Override public Set<Answer> findByUserId(long userId) {
        final long start = nanoTime();
        Set<Answer> answers = new LinkedHashSet<>();
        try {
            log.info(format("Find User by ID: %d", userId));
            answers.addAll(getDao().findByUserId(userId));
            log.info(format("Set of Users have size: %d", answers.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        return answers;
    }

    @Override public Set<Answer> findByQuestionId(long questionId) {
        final long start = nanoTime();
        HashSet<Answer> answers = new LinkedHashSet<>();
        try {
            log.info(format("Find Answers by Question ID: %d", questionId));
            answers.addAll(getDao().findByQuestionId(questionId));
            log.info(format("Set of Questions have size: %d", answers.size()));

        } catch (Exception e) {
            log.error("Error: ", e);
        }
        log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        return answers;
    }
}
