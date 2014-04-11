package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.Question;

import java.util.Set;

public interface QuestionService extends GenericService<Long, Question> {

    Set<Question> loadAll(Integer offset);

    Set<Question> findQuestionsByUserId(Long userId);

    Set<Question> findQuestionsByTags(String... tags);
}
