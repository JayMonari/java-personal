package com.jay.dao;

import java.util.Optional;

import com.jay.dto.LoginRequest;
import com.jay.model.User;

public interface AuthDao {
  public Optional<User> getByCredentials(LoginRequest loginRequest);
}
