package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.Answer;

import java.util.Set;

public interface AnswerService extends GenericService<Long, Answer> {

    Set<Answer> findAnswersByUserId(Long userId);

    Set<Answer> findAnswersByQuestionId(Long questionId);
}
