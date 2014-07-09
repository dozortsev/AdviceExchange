package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;

import java.util.Set;

public interface QuestionService extends GenericService<Long, Question> {

    int addAnswer(Answer answer);

    int delAnswer(Answer answer);

    Set<Question> findByKeyWords(String... keyWords);

    Set<Question> loadFrom(int offset);

    Set<Question> findByUserId(long userId);

    Set<Question> findByTags(String... tags);
}
