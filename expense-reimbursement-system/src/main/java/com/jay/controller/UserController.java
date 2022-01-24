package com.jay.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.jay.dao.UserDao;
import com.jay.model.User;
import com.jay.model.UserRole;
import com.jay.util.EmailUtil;
import com.jay.util.PBEUtil;

import io.javalin.http.Context;

public class UserController {
  private static UserDao userDao;

  public UserController(UserDao userDao) {
    UserController.userDao = userDao;
  }

  public static void getHomePage(Context context) {
      String path = System.getProperty("user.dir") + "/frontend/html/employee.html";
      try {
        String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
        context.html(content);
      } catch (IOException e) {
        e.printStackTrace();
      }
  }

  public static void getManagerHome(Context context) {
    String path = System.getProperty("user.dir") + "/frontend/html/manager.html";
    try {
      String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
      context.html(content);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //TODO Destory after done
  public void forDemoPurposes() {
    String salt1 = PBEUtil.createSalt(45);
    String encryptedPassword1 =
        PBEUtil.generateSecurePassword("Pass1234", salt1);
    User user = User.builder()
        .username("ers_user")
        .password(salt1 + encryptedPassword1)
        .email("ers_user@ers.net")
        .firstName("UserFirst")
        .lastName("UserLast")
        .userRole(UserRole.USER)
        .build();
    userDao.insert(user);

    String salt2 = PBEUtil.createSalt(45);
    String encryptedPassword2 =
        PBEUtil.generateSecurePassword("Pass1234", salt2);
    User manager = User.builder()
        .username("ers_manager")
        .password(salt2 + encryptedPassword2)
        .email("ers_finman@ers.net")
        .firstName("ManagerFirst")
        .lastName("ManagerLast")
        .userRole(UserRole.MANAGER)
        .build();
    userDao.insert(manager);

    String salt3 = PBEUtil.createSalt(45);
    String encryptedPassword3 =
        PBEUtil.generateSecurePassword("Pass1234", salt3);
    User manager2 = User.builder()
        .username("ers_manager2")
        .password(salt3 + encryptedPassword3)
        .email("ers_finman2@ers.net")
        .firstName("Manager2First")
        .lastName("Manager2Last")
        .userRole(UserRole.MANAGER)
        .build();
    userDao.insert(manager2);
    // EmailUtil.buildEmailFor(user);
    // EmailUtil.buildEmailFor(manager);
    // EmailUtil.buildEmailFor(manager2);
  }
}
