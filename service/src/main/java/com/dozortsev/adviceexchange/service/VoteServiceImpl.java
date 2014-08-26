package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.dao.GenericDao;
import com.dozortsev.adviceexchange.dao.VoteDao;
import com.dozortsev.adviceexchange.domain.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Transactional(propagation = REQUIRES_NEW)
@Service
public class VoteServiceImpl extends GenericServiceImpl<Long, Vote> implements VoteService {

    private @Autowired VoteDao voteDao;

    public VoteServiceImpl() {
        setEntityClass(Vote.class);
    }

    @Override public GenericDao<Long, Vote> getDao() {
        return voteDao;
    }
}
