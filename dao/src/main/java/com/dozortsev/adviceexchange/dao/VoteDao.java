package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.tables.TVote;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.VoteRecord;

public interface VoteDao extends GenericDao<VoteRecord, TVote> {
}
