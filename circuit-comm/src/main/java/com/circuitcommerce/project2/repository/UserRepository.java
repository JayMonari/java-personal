package com.circuitcommerce.project2.repository;

import java.util.Optional;

import com.circuitcommerce.project2.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  public Optional<User> findByUsername(String username);

  public Optional<User> findByUsernameAndEmail(String username, String email);
}
