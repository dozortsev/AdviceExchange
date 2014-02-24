package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Answer;

import java.util.List;

public interface AnswerDao extends GenericDao<Long, Answer> {

    List<Answer> findAnswersByUserId(Long userId);

    List<Answer> findAnswersByQuestionId(Long questionId);
}
