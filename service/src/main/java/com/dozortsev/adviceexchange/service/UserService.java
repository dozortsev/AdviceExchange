package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.domain.UserActivity;

import java.util.Set;

public interface UserService extends GenericService<Long, User> {

    User findUserByLogin(String login);

    Set<User> findUsersByName(String name);

    Set<UserActivity> userActivities(Long id);
}
