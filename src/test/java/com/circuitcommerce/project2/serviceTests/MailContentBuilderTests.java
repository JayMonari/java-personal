package com.circuitcommerce.project2.serviceTests;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.circuitcommerce.project2.service.MailContentBuilder;

@SpringBootTest
public class MailContentBuilderTests {

  private MailContentBuilder mcbuilder;
  @BeforeEach
  public void before(){

  }
  @AfterEach
  public void after(){

  }

  @Test
  public void NotNullTest(){
    assertThat(mcbuilder).isNotNull();
  }
    
}
