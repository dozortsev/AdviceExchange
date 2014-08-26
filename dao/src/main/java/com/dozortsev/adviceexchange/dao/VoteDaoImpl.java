package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.Vote;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class VoteDaoImpl extends GenericDaoImpl<Long, Vote> implements VoteDao {

    public VoteDaoImpl() {
        setEntityClass(Vote.class);
    }
}
