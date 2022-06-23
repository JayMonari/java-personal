package com.circuitcommerce.project2.serviceTests;

import static org.assertj.core.api.Assertions.assertThat;

import com.circuitcommerce.project2.model.User;
import com.circuitcommerce.project2.repository.UserRepository;
import com.circuitcommerce.project2.service.UserDetailsServiceImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
public class UserDetailsServiceTests {
  @Mock
  private UserRepository userRepo;
  @InjectMocks
  private UserDetailsServiceImpl userServ;

  private User user;
  private  UserDetails useDet;

  private Long userId=(long)12345;
  private String username ="OliverClotheson";
  private String password="MyNewClothesCameFromTheEmporer";
  private String email="mesofunny@comedygold.com";
  private String firstName="Oliver";
  private String lastName="Clotheson";


  @BeforeEach
  public void before(){
    user=new User(userId, username, password, email, firstName, lastName, null, true, null, null, null, null);
    
  }
  @AfterEach
  public void after(){

  }

  @Test
  public void NotNullTest(){
    assertThat(userServ).isNotNull();
  }

}
