package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.User;
import com.dozortsev.adviceexchange.domain.jooq.tables.pojos.UserActivity;

import java.util.Set;

public interface UserService extends GenericService<User> {

    int totalCount(String name);

    Set<User> findByName(String name, int offset);

    User findByLogin(String login);

    Set<UserActivity> userActivities(int id);
}
