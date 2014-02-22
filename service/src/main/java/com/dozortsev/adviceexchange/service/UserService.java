package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.User;

public interface UserService extends GenericService<Long, User> {

    public User findUserByLogin(String login);
}
