package com.dozortsev.adviceexchange.service;

import com.dozortsev.adviceexchange.domain.User;

public interface UserService extends GenericService<Long, User> {

    User findUserByLogin(String login);
}
