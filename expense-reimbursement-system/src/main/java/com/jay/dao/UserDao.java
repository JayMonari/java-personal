package com.jay.dao;

import java.util.Optional;

import com.jay.model.User;

public interface UserDao extends GenericDao<User, Integer> {
  public Optional<User> findByUsername(String username);
}
