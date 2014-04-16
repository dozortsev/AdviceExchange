package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.domain.UserActivity;

import java.util.List;

public interface UserDao extends GenericDao<Long, User> {

    List<User> loadFrom(Integer offset);

    User findUserByLogin(String login);

    List<User> findUsersByName(String name);

    List<UserActivity> userActivities(Long id);
}
