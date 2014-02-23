package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Question;

import java.util.List;

public interface QuestionDao extends GenericDao<Long, Question> {

    List<Question> findQuestionsByUserId(Long userId);

    List<Question> findQuestionsByTagId(Long... tagId);
}
