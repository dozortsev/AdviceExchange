package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.tables.User;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.UserActivityRecord;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.UserRecord;

import java.util.List;

public interface UserDao extends GenericDao<UserRecord, User> {

    int totalCount(String name);

    List<UserRecord> findByName(String name, int offset);

    UserRecord findByLogin(String login);

    List<UserActivityRecord> userActivities(int id);
}
