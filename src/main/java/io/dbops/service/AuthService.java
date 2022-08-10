package io.dbops.service;

import io.dbops.model.User;

public interface AuthService {

    public User login(String username);
}
