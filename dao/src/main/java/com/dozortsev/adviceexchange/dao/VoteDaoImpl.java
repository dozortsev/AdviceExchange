package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.tables.TVote;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.VoteRecord;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Transactional(propagation = MANDATORY)
@Repository
public class VoteDaoImpl extends GenericDaoImpl<VoteRecord, TVote> implements VoteDao {

    public VoteDaoImpl() {
        setTable(TVote.VOTE);
        setIdField(TVote.VOTE.ID);
    }
}
