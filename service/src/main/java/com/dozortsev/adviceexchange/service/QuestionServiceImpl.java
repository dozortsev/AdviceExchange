package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.QuestionDao;
import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.lang.String.format;
import static java.lang.System.nanoTime;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
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

    @Override public int addAnswer(Answer answer) {
        final long start = nanoTime();
        Question question = answer.getQuestion();
        if (question != null) {
            try {
                log.info(format("Add Answer to Question ID: %d", question.getId()));
                getDao().addAnswer(question, answer);
                log.info(format("Success create; Answer ID: %d", answer.getId()));
            }
            catch (Exception e) {
                log.error("Error: ", e);
            }
            finally {
                log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
            }
            return question.getAnswerCount();
        }
        else throw new NullPointerException(
                "Answer should have related question"
        );
    }

    @Override public int delAnswer(Answer answer) {
        final long start = nanoTime();
        Question question = answer.getQuestion();
        try {
            log.info(format("Delete Answer ID: %d form Question ID: %d", answer.getId(), question.getId()));
            getDao().delAnswer(question, answer);
            log.info("Success delete");
        }
        catch (Exception e) {
            log.error("Error: ", e);
        }
        finally {
            log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        }
        return question.getAnswerCount();
    }

    @Override public Set<Question> findByKeyWords(String... keyWords) {
        final long start = nanoTime();
        Set<Question> questions = new LinkedHashSet<>();
        try {
            log.info(format("Find Questions by key words: %s", Arrays.toString(keyWords)));
            questions.addAll(getDao().findByKeyWord(keyWords));
            log.info(format("Set of Questions have size: %d", questions.size()));
        }
        catch (Exception e) {
            log.error("Error: ", e);
        }
        finally {
            log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        }
        return questions;
    }

    @Transactional(readOnly = true)
    @Override public LinkedHashSet<Question> loadFrom(int offset) {
        final long start = nanoTime();
        LinkedHashSet<Question> questions = new LinkedHashSet<>();
        try {
            log.info(format("Load Questions from: %d; row count: 10", offset));
            questions.addAll(getDao().loadFrom(offset));
            log.info(format("Set of Questions have size: %d", questions.size()));
        }
        catch (Exception e) {
            log.error("Error: ", e);
        }
        finally {
            log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        }
        return questions;
    }

    @Transactional(readOnly = true)
    @Override public Set<Question> findByUserId(long userId) {
        final long start = nanoTime();
        Set<Question> questions = new LinkedHashSet<>();
        try {
            log.info(format("Find Questions by User ID: %d", userId));
            questions.addAll(getDao().findByUserId(userId));
            log.info(format("Set of Questions have size: %d", questions.size()));
        }
        catch (Exception e) {
            log.error("Error: ", e);
        }
        finally {
            log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        }
        return questions;
    }

    @Transactional(readOnly = true)
    @Override public Set<Question> findByTags(String... tags) {
        final long start = nanoTime();
        Set<Question> questions = new LinkedHashSet<>();
        try {
            log.info(format("Find Questions by Tags: %s", Arrays.toString(tags)));
            questions.addAll(getDao().findByTags(tags));
            log.info(format("Set of Questions have size: %d", questions.size()));
        }
        catch (Exception e) {
            log.error("Error: ", e);
        }
        finally {
            log.info(format("Time lapse: %d", NANOSECONDS.toMillis(nanoTime() - start)));
        }
        return questions;
    }
}
