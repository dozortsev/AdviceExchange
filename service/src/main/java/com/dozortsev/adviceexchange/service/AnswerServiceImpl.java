package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.AnswerDao;
import com.dozortsev.adviceexchange.domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW)
@Service
public class AnswerServiceImpl extends GenericServiceImpl<Long, Answer> implements AnswerService {

    @Autowired private AnswerDao answerDao;

    @Override public AnswerDao getDao() {
        return answerDao;
    }

    @Override public Set<Answer> findAnswersByUserId(Long userId) {
        return getDao().findAnswersByUserId(userId);
    }
}
