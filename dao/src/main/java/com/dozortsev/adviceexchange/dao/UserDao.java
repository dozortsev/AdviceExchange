package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.domain.UserActivity;

import java.util.List;

public interface UserDao extends GenericDao<Long, User> {

    int totalCount(String name);

    List<User> findByName(String name, int offset);

    User findByLogin(String login);

    List<UserActivity> userActivities(long id);
}
