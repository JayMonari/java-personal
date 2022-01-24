package com.jay.dao;

import java.util.Optional;

import com.jay.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDaoImpl implements UserDao {
  private final SessionFactory sessionFactory;

  public UserDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void insert(User user) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.beginTransaction();
      session.save(user);
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public Optional<User> findByUsername(String username) {
    User user = null;
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.beginTransaction();
      user = (User) session.createQuery("FROM User WHERE username=:username")
          .setParameter("username", username)
          .uniqueResult();
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.ofNullable(user);
  }
}
