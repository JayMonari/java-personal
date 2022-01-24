package com.jay.service;

import com.jay.dao.AuthDao;
import com.jay.dto.LoginRequest;
import com.jay.exception.NoSuchUserException;
import com.jay.model.User;

public class AuthService {
  private final AuthDao authDao;

  public AuthService(AuthDao authDao) {
    this.authDao = authDao;
  }

  public User login(LoginRequest loginRequest) {
    return authDao.getByCredentials(loginRequest)
        .orElseThrow(() -> new NoSuchUserException(
            "Could Not Find User with username = "
            + loginRequest.getUsername() + "and password = "
            + loginRequest.getPassword()));
  }
}
