
package com.circuitcommerce.project2.serviceTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.circuitcommerce.project2.service.AuthService;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class AuthServiceTests {
  AuthService auserv;

  @BeforeAll
  public static void start(){
    
  }
  @AfterAll
  public static void end(){

  }
  @BeforeEach
  public void before(){

  }
  @AfterEach
  public void after(){

  }

  @Test
  public void NotNullTest(){
    assertTrue(true); 
  }
}
