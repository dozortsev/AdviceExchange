package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Question;

import java.util.List;

public interface QuestionDao extends GenericDao<Long, Question> {

    List<Question> loadAll(Integer offset, Integer rowCount);

    List<Integer> answersCount(Integer offset, Integer rowCount);

    List<Question> findQuestionsByUserId(Long userId);

    List<Question> findQuestionsByTags(String... tags);
}
