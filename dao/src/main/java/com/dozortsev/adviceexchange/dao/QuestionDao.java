package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Question;

import java.util.Set;

public interface QuestionDao extends GenericDao<Long, Question> {

    Set<Question> findQuestionByUserId(Long userId);
}
