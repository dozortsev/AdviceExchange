package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.Question;

import java.util.LinkedHashMap;
import java.util.Set;

public interface QuestionService extends GenericService<Long, Question> {

    LinkedHashMap<Question, Integer> loadAll(Integer offset, Integer rowCount);

    Set<Question> findQuestionsByUserId(Long userId);

    Set<Question> findQuestionsByTags(String... tags);
}
