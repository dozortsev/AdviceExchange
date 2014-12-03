package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.tables.TQuestion;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.Question;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.QuestionRecord;

import java.util.List;

public interface QuestionDao extends GenericDao<QuestionRecord, TQuestion> {

    List<Question> loadFrom(int offset);

    List<Question> findByKeyWord(String... keyWords);

    List<Question> findByUserId(int userId);

    List<Question> findByTags(String... tags);
}
