package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.jooq.tables.TUser;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.User;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.UserActivity;
import com.dozortsev.adviceexchange.domain.jooq.tables.records.UserRecord;

import java.util.List;

public interface UserDao extends GenericDao<UserRecord, TUser> {

    int totalCount(String name);

    List<User> findByName(String name, int offset);

    User findByLogin(String login);

    List<UserActivity> userActivities(int id);
}
