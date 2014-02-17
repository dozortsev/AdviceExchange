package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Answer;

import java.util.Set;

public interface AnswerDao extends GenericDao<Long, Answer> {

    Set<Answer> findAnswersByUserId(Long userId);
}
