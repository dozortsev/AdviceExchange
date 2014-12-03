package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Question;

import java.util.Set;

public interface QuestionService extends GenericService<Question> {

    Set<Question> findByKeyWords(String... keyWords);

    Set<Question> loadFrom(int offset);

    Set<Question> findByUserId(int userId);

    Set<Question> findByTags(String... tags);
}
