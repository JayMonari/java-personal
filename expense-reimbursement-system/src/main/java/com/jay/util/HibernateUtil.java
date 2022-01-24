package com.jay.util;

import com.jay.model.Reimbursement;
import com.jay.model.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
  private static SessionFactory sessionFactory;
  private static final CustomNamingStrategy customNamingStrategy =
    new CustomNamingStrategy();

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      Configuration configuration = new Configuration()
          .addAnnotatedClass(Reimbursement.class)
          .addAnnotatedClass(User.class)
          .configure();
      configuration.setPhysicalNamingStrategy(customNamingStrategy);
      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
          .applySettings(configuration.getProperties())
          .build();
      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    return sessionFactory;
  }
}