package com.jay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.jay.dao.AuthDao;
import com.jay.dao.AuthDaoImpl;
import com.jay.dto.LoginRequest;
import com.jay.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AuthDaoTest {
  @Mock
  SessionFactory sessFac;

  @Mock
  Session sess;

  @Mock
  Transaction tx;

  @Mock
  Query<User> query;

  private LoginRequest mockLogin;
  private User mockUser;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mockLogin = new LoginRequest("username", "Pass1234");
    mockUser = User.builder().password("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii").build();
    // when(conf.buildSessionFactory()).thenReturn(sessFac);
    when(sessFac.openSession()).thenReturn(sess);
    when(sess.createQuery(anyString())).thenReturn(query);
    when(sess.beginTransaction()).thenReturn(tx);
    when(query.setParameter(anyString(), anyString())).thenReturn(query);
    when(query.uniqueResult()).thenReturn(mockUser);
  }

  @Test
  void testGetByCredentials() {
    AuthDao mockDao = new AuthDaoImpl(sessFac);
    assertEquals(
      mockDao.getByCredentials(mockLogin),
      Optional.empty());
  }
}
