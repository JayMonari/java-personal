package com.circuitcommerce.project2.controllerTests;

import java.util.ArrayList;

import com.circuitcommerce.project2.controller.ComputerCaseController;
import com.circuitcommerce.project2.service.ComputerCaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.circuitcommerce.project2.dto.ComputerCaseDto;
import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.model.ComputerCase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class CaseControllerTests {
  private MockMvc mock;
  @Mock
  private ComputerCaseService caseServ;
  @InjectMocks
  private ComputerCaseController caseCont;

  private ArrayList<ProductShortDto> listEmpty;
  private ArrayList<ProductShortDto> listFound;
  private ComputerCase case1;
  private ComputerCaseDto caseDto;
  private ProductShortDto pos;

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

  @BeforeEach
  public void before(){
    case1=new ComputerCase(moboForm, frontPorts, psMounted, sidePannelWindow, fans, internalDriveBays);
    case1.setBrand(brand);
    case1.setProductId(productId);
    case1.setModelNumber(modelNumber);
    case1.setTitle(title);
    case1.setPrice(price);

    caseDto=new ComputerCaseDto(moboForm, frontPorts, moboForm, sidePannelWindow, fans, internalDriveBays);
    caseDto.setBrand(brand);
    caseDto.setModelNumber(modelNumber);
    caseDto.setTitle(title);
    caseDto.setPrice(price);

    pos=new ProductShortDto(productId, title, brand, price, modelNumber);

    listEmpty=new ArrayList<>();
    listFound=new ArrayList<>();

    listFound.add(pos);

    mock=MockMvcBuilders.standaloneSetup(caseCont).build();

    doNothing().when(caseServ).insertComputerCase(caseDto);
    when(caseServ.getComputerCaseById(productId)).thenReturn(case1);
    when(caseServ.getallComputerCases()).thenReturn(listFound);
    when(caseServ.findByBrand(brand)).thenReturn(listFound);
    when(caseServ.findByBrand("Compuserv")).thenReturn(listEmpty);
    when(caseServ.findBetweenPrice(100, 200)).thenReturn(listFound);
    when(caseServ.findBetweenPrice(50, 100)).thenReturn(listEmpty);
  }

  @AfterEach
  public void after(){

  }

  @Test
  public void notNullTest() throws Exception{
    assertThat(caseCont).isNotNull();
  }

  @Test
  public void getAllTest() throws Exception{
    mock.perform(get("/computercase/all")).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  void getbyProductIdTest() throws Exception{
    mock.perform(
      get("/computercase/id/{id}",case1.getProductId())).
      andExpect(status().isOk()).
      andExpect(jsonPath("$.brand",is(brand))).
      andExpect(jsonPath("$.modelNumber", is(modelNumber))).
      andExpect(jsonPath("$.title", is(title))).
      andExpect(jsonPath("$.price", is(price))).
      
      andExpect(jsonPath("$.motherboardCompatibility", is(case1.getMotherboardCompatibility()))).
      andExpect(jsonPath("$.frontPorts", is(case1.getFrontPorts()))).
      andExpect(jsonPath("$.powerSupplyMounted", is(case1.getPowerSupplyMounted()))).
      andExpect(jsonPath("$.sidePannelWindow", is(case1.getSidePannelWindow()))).
      andExpect(jsonPath("$.fans", is(case1.getFans()))).
      andExpect(jsonPath("$.internalDriveBays", is(case1.getInternalDriveBays())));
  }

  @Test
  public void getByBrandTest1() throws Exception{
    mock.perform(get("/computercase/brand/{brand}",case1.getBrand())).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test
  public void getByBrandTest2() throws Exception{
    mock.perform(get("/computercase/brand/{brand}","Compuserv")).
    andExpect(status().isOk());
  }

  @Test 
  public void getByPriceBetweenTest1() throws Exception{
    mock.perform(get("/computercase/pricelow/{pricelow}/pricehigh/{pricehigh}",100, 200)).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  public void getByPriceBetweenTest2() throws Exception{
    mock.perform(get("/computercase/pricelow/{pricelow}/pricehigh/{pricehigh}",50, 100)).
    andExpect(status().isOk());
  }

  @Test
  public void postCaseTest() throws Exception{
    mock.perform(post("/computercase/add" ).
    contentType(MediaType.APPLICATION_JSON).
    content(new ObjectMapper().writeValueAsString(case1))).
    andExpect(status().isCreated());
  }
}
