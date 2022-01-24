package com.jay.dao;

import java.util.Optional;

import com.jay.dto.LoginRequest;
import com.jay.exception.InvalidPasswordException;
import com.jay.model.User;
import com.jay.util.PBEUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AuthDaoImpl implements AuthDao {
  private final SessionFactory sessionFactory;

  public AuthDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public Optional<User> getByCredentials(LoginRequest loginRequest) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.beginTransaction();
      User user = (User) session.createQuery("FROM User WHERE username=:username")
          .setParameter("username", loginRequest.getUsername())
          .uniqueResult();
      transaction.commit();
      String securedPassword = user.getPassword().substring(45);
      String salt = user.getPassword().substring(0, 45);
      boolean passwordsMatch = PBEUtil.verifyUserPassword(loginRequest.getPassword(), securedPassword, salt);
      if (!passwordsMatch) {
        throw new InvalidPasswordException();
      }
      return Optional.ofNullable(user);
    } catch (InvalidPasswordException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }
}
