package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;

import java.util.List;

public interface QuestionDao extends GenericDao<Long, Question> {

    int addAnswer(Question question, Answer answer);

    int delAnswer(Question question, Answer answer);

    List<Question> loadFrom(int offset);

    List<Question> findByKeyWord(String... keyWork);

    List<Question> findByUserId(long userId);

    List<Question> findByTags(String... tags);
}
