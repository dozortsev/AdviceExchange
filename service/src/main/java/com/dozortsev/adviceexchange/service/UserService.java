package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.domain.UserActivity;

import java.util.Set;

public interface UserService extends GenericService<Long, User> {

    Set<User> findUsersByName(String name, Integer offset);

    User findUserByLogin(String login);

    Set<UserActivity> userActivities(Long id);
}
