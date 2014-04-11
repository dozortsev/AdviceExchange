package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;

import java.util.Set;

public interface QuestionService extends GenericService<Long, Question> {

    Integer addAnswer(Question question, Answer answer);

    Integer delAnswer(Question question, Answer answer);

    Set<Question> loadAll(Integer offset);

    Set<Question> findQuestionsByUserId(Long userId);

    Set<Question> findQuestionsByTags(String... tags);
}
