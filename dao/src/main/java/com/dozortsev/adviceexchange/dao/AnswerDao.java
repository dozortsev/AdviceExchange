package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.tables.TAnswer;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Answer;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.AnswerRecord;

import java.util.List;

public interface AnswerDao extends GenericDao<AnswerRecord, TAnswer> {

    List<Answer> findByUserId(int userId);

    List<Answer> findByQuestionId(int questionId);
}
