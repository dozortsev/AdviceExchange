package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Answer;
import com.dozortsev.adviceexchange.domain.Question;

import java.util.List;

public interface QuestionDao extends GenericDao<Long, Question> {

    Integer addAnswer(Question question, Answer answer);

    Integer delAnswer(Question question, Answer answer);

    List<Question> loadFrom(Integer offset);

    List<Question> findQuestionsByUserId(Long userId);

    List<Question> findQuestionsByTags(String... tags);
}
