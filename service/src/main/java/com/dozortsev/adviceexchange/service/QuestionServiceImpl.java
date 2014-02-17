package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.QuestionDao;
import com.dozortsev.adviceexchange.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW)
@Service
public class QuestionServiceImpl extends GenericServiceImpl<Long, Question> implements QuestionService {

    @Autowired private QuestionDao questionDao;

    @Override public QuestionDao getDao() {
        return questionDao;
    }

    @Override public Set<Question> findQuestionByUserId(Long userId) {
        return getDao().findQuestionByUserId(userId);
    }
}
