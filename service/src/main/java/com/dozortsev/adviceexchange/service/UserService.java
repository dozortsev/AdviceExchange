package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.User;
import com.dozortsev.adviceexchange.domain.UserActivity;

import java.util.Set;

public interface UserService extends GenericService<Long, User> {

    Integer totalCount(String name);

    Set<User> findByName(String name, Integer offset);

    User findByLogin(String login);

    Set<UserActivity> userActivities(Long id);
}
