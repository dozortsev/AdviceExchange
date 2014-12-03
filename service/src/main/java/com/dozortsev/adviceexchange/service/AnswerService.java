package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Answer;

import java.util.Set;

public interface AnswerService extends GenericService<Answer> {

    Set<Answer> findByUserId(int userId);

    Set<Answer> findByQuestionId(int questionId);
}
