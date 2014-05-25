package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.domain.UserActivity;

import java.util.Set;

public interface UserService extends GenericService<Long, User> {

    int totalCount(String name);

    Set<User> findByName(String name, int offset);

    User findByLogin(String login);

    Set<UserActivity> userActivities(long id);
}
