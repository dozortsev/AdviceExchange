package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.domain.UserActivity;

import java.util.List;

public interface UserDao extends GenericDao<Long, User> {

    User findUserByLogin(String login);

    List<UserActivity> userActivities(Long id);
}
