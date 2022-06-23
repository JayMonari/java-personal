package com.circuitcommerce.project2.controllerTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;

import com.circuitcommerce.project2.controller.CentralProcessingUnitController;
import com.circuitcommerce.project2.dto.CentralProcessingUnitDto;
import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.model.CentralProcessingUnit;
import com.circuitcommerce.project2.service.CentralProcessingUnitService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
public class CPUControllerTests {
  
  private MockMvc mock;

  @Mock
  private CentralProcessingUnitService cpuServ;
  @InjectMocks
  private CentralProcessingUnitController cpuCont;
  private CentralProcessingUnit cpu;
  private CentralProcessingUnitDto cpuDto;
  private ArrayList<ProductShortDto> listEmpty;
  private ArrayList<ProductShortDto> listFound;

  private String series="Core i7 4th Gen";
  private String l3Cache="8MB";
  private String l2Cache="4x256KB";
  private String coolingDevice="";
  private String manufacturingTech="22nm";

  private String brand="Intel";
  private Long productId=(long)12345;
  private String modelNumber="BX80646I74790K";
  private String title="Intel Core i7-4790K Devil's Canyon Quad-Core 4.0 GHz LGA 1150 88W BX80646I74790K Desktop Processor Intel HD Graphics 4600";
  private Double price=339.99;
  private ProductShortDto pos;

  @BeforeEach
  public void before(){
    cpu=new CentralProcessingUnit(series, l3Cache, l2Cache, coolingDevice, manufacturingTech);
    cpu.setBrand(brand);
    cpu.setProductId(productId);
    cpu.setModelNumber(modelNumber);
    cpu.setTitle(title);
    cpu.setPrice(price);

    cpuDto=new CentralProcessingUnitDto(series, l3Cache, l2Cache, coolingDevice, manufacturingTech);
    cpuDto.setBrand(brand);
    cpuDto.setModelNumber(modelNumber);
    cpuDto.setTitle(title);
    cpuDto.setPrice(price);

    pos=new ProductShortDto(productId, title, brand, price, modelNumber);

    listEmpty=new ArrayList<>();
    listFound=new ArrayList<>();

    listFound.add(pos);

    mock=MockMvcBuilders.standaloneSetup(cpuCont).build();

    doNothing().when(cpuServ).insertCpu(cpuDto);
    when(cpuServ.getCpuById(productId)).thenReturn(cpu);
    when(cpuServ.getAllCpus()).thenReturn(listFound);
    when(cpuServ.findByBrand(brand)).thenReturn(listFound);
    when(cpuServ.findByBrand("AMD")).thenReturn(listEmpty);
    when(cpuServ.findBetweenPrice(300, 400)).thenReturn(listFound);
    when(cpuServ.findBetweenPrice(200, 300)).thenReturn(listEmpty);
  }

  @AfterEach
  public void after(){

  }

  @Test
  public void notNullTest() throws Exception{
    assertThat(cpuCont).isNotNull();
  }

  @Test
  public void getAllTest() throws Exception{
    mock.perform(get("/cpu/all")).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  void getbyProductIdTest() throws Exception{
    mock.perform(
      get("/cpu/id/{id}",cpu.getProductId())).
      andExpect(status().isOk()).
      andExpect(jsonPath("$.brand",is(brand))).
      andExpect(jsonPath("$.modelNumber", is(modelNumber))).
      andExpect(jsonPath("$.title", is(title))).
      andExpect(jsonPath("$.price", is(price))).

      andExpect(jsonPath("$.series", is(cpu.getSeries()))).
      andExpect(jsonPath("$.l3Cache", is(cpu.getL3Cache()))).
      andExpect(jsonPath("$.l2Cache", is(cpu.getL2Cache()))).
      andExpect(jsonPath("$.coolingDevice", is(cpu.getCoolingDevice()))).
      andExpect(jsonPath("$.manufacturingTech", is(cpu.getManufacturingTech())));
  }

  @Test
  public void getByBrandTest1() throws Exception{
    mock.perform(get("/cpu/brand/{brand}",cpu.getBrand())).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test
  public void getByBrandTest2() throws Exception{
    mock.perform(get("/cpu/brand/{brand}","AMD")).
    andExpect(status().isOk());
  }

  @Test 
  public void getByPriceBetweenTest1() throws Exception{
    mock.perform(get("/cpu/pricelow/{pricelow}/pricehigh/{pricehigh}",300,400)).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  public void getByPriceBetweenTest2() throws Exception{
    mock.perform(get("/cpu/pricelow/{pricelow}/pricehigh/{pricehigh}",200,300)).
    andExpect(status().isOk());
  }

  @Test
  public void postCPUTest() throws Exception{
    mock.perform(post("/cpu/add" ).
    contentType(MediaType.APPLICATION_JSON).
    content(new ObjectMapper().writeValueAsString(cpu))).
    andExpect(status().isCreated());
  }

}
