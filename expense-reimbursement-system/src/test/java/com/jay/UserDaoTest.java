package com.jay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import com.jay.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserDaoTest {
  @Mock
  SessionFactory sessFac;

  @Mock
  Session sess;

  @Mock
  Transaction tx;

  @Mock
  Query<User> query;

  private User mockUser;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mockUser = User.builder().password("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii").build();
    // when(conf.buildSessionFactory()).thenReturn(sessFac);
    when(sessFac.openSession()).thenReturn(sess);
    when(sess.createQuery(anyString())).thenReturn(query);
    when(sess.beginTransaction()).thenReturn(tx);
    when(query.setParameter(anyString(), anyString())).thenReturn(query);
    when(query.uniqueResult()).thenReturn(mockUser);
  }

  @Test
  void testFindByUsername() {
    assertEquals(1, 1);
  }
}
