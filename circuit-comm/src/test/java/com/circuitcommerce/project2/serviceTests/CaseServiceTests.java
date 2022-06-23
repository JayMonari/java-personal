package com.circuitcommerce.project2.serviceTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.model.ComputerCase;
import com.circuitcommerce.project2.repository.ComputerCaseRepository;
import com.circuitcommerce.project2.service.ComputerCaseService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CaseServiceTests {

  private ComputerCase case1;
  @InjectMocks
  private ComputerCaseService caseServ;
  private ArrayList<ProductShortDto> listfound;
  private ArrayList<ProductShortDto> listEmpty;

  @Mock
  private ComputerCaseRepository caseRepo;

  private String moboForm="ATX Full-Tower";
  private String frontPorts="9-5.25";
  private String psMounted="Bottom";
  private String sidePannelWindow="Yes";
  private String fans="2x120mm LED fan(1200RPM 17dBA)";
  private String internalDriveBays="13-5.25";
  
  private String brand="CoolerMaster";
  private Long productId=(long)12345;
  private String modelNumber="SGC-5000-KWN1";
  private String title="CM Storm Trooper - Gaming Full Tower Computer Case with Handle and External 2.5\" Drive Dock with Side Panel Window";
  private Double price=164.99;
  private ProductShortDto pos;

  
  @BeforeEach
  public void before(){
    case1=new ComputerCase(moboForm, frontPorts, psMounted, sidePannelWindow, fans, internalDriveBays);
    case1.setBrand(brand);
    case1.setProductId(productId);
    case1.setModelNumber(modelNumber);
    case1.setTitle(title);
    case1.setPrice(price);

    pos=new ProductShortDto(productId, title, brand, price, null);

    listfound=new ArrayList<>();
    listEmpty=new ArrayList<>();
    listfound.add(pos);

    when(caseRepo.getOne((long)12345)).thenReturn(case1);
    when(caseRepo.caseSmall()).thenReturn(listfound);
    when(caseRepo.findByBrand(brand)).thenReturn(listfound);
    when(caseRepo.findByBrand("CasusMaximus")).thenReturn(listEmpty);
    when(caseRepo.findByPriceBetween(150.00,180.00)).thenReturn(listfound);
    when(caseRepo.findByPriceBetween(190.00,200.00)).thenReturn(listEmpty);

  }
  @AfterEach
  public void after(){

  }

  @Test
  public void NotNullTest(){
    assertThat(caseServ).isNotNull();
  }
  @Test
  public void getComputerCaseByIdTest() throws Exception{
    ComputerCase test=caseServ.getComputerCaseById((long)12345);
    assertEquals(case1,test);
  }
  @Test
  public void getallComputerCasesTest() throws Exception{
    assertArrayEquals(caseServ.getallComputerCases().toArray(), listfound.toArray());
  }
  @Test
  public void findByBrandTest1()throws Exception{
    assertArrayEquals(caseServ.findByBrand(brand).toArray(), listfound.toArray());
  }
  @Test
  public void findByBrandTest2()throws Exception{
    assertArrayEquals(caseServ.findByBrand("CasusMaximus").toArray(), listEmpty.toArray());
  }
  @Test
  public void findByPriceBetweenTest1() throws Exception{
    assertArrayEquals(caseServ.findBetweenPrice(150.00,180.00).toArray(),listfound.toArray() );
  }

  @Test
  public void findByPriceBetweenTest2() throws Exception{
    assertArrayEquals(caseServ.findBetweenPrice(190.00,200.00).toArray(),listEmpty.toArray() );
  }
}
