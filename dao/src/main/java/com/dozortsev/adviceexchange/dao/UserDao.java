package com.dozortsev.adviceexchange.dao;

import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.domain.UserActivity;

import java.util.List;

public interface UserDao extends GenericDao<Long, User> {

    Integer totalCount(String name);

    List<User> findByName(String name, Integer offset);

    User findByLogin(String login);

    List<UserActivity> userActivities(Long id);
}
