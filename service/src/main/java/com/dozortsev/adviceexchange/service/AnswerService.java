package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.Answer;

import java.util.Set;

public interface AnswerService extends GenericService<Long, Answer> {

    Set<Answer> findByUserId(long userId);

    Set<Answer> findByQuestionId(long questionId);
}
