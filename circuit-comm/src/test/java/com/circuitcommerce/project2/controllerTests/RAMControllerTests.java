package com.circuitcommerce.project2.controllerTests;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.circuitcommerce.project2.controller.RandomAccessMemoryController;
import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.dto.RandomAccessMemoryDto;
import com.circuitcommerce.project2.model.RandomAccessMemory;
import com.circuitcommerce.project2.service.RandomAccessMemoryService;
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
public class RAMControllerTests {
  private MockMvc mock;

  @Mock
  private RandomAccessMemoryService ramServ;
  @InjectMocks
  private RandomAccessMemoryController ramCont;
  private RandomAccessMemory ram;
  private RandomAccessMemoryDto ramDto;
  private ArrayList<ProductShortDto> listEmpty;
  private ArrayList<ProductShortDto> listFound;

  private Integer caseLatency=10;
  private String voltage="1.5V";
  private String multiChannelKit="Quad Channel Kit";
  private String timing="10-11-10-30";

  private String brand="GSKILL";
  private Long productId=(long)12345;
  private String modelNumber="F3-14900CL10Q-32GBZL";
  private String title= "IG.SKILL Ripjaws Z Series 32GB (4 x 8GB) 240-Pin DDR3 SDRAM DDR3 1866 (PC3 14900) Desktop Memory Model F3-14900CL10Q-32GBZL";
  private Double price=134.99;  
  private ProductShortDto pos;
  
  @BeforeEach
  public void before(){
    ram=new RandomAccessMemory(caseLatency, voltage, multiChannelKit, timing);
    ram.setBrand(brand);
    ram.setProductId(productId);
    ram.setModelNumber(modelNumber);
    ram.setTitle(title);
    ram.setPrice(price);

    ramDto=new RandomAccessMemoryDto(caseLatency, voltage, multiChannelKit, timing);
    ramDto.setBrand(brand);
    ramDto.setModelNumber(modelNumber);
    ramDto.setTitle(title);
    ramDto.setPrice(price);

    pos=new ProductShortDto(productId, title, brand, price, modelNumber);

    listEmpty=new ArrayList<>();
    listFound=new ArrayList<>();

    listFound.add(pos);

    mock=MockMvcBuilders.standaloneSetup(ramCont).build();

    doNothing().when(ramServ).insertRAM(ramDto);
    when(ramServ.getRandomAccessMemoryById(productId)).thenReturn(ram);
    when(ramServ.getAllRandomAccessMemories()).thenReturn(listFound);
    when(ramServ.findByBrand(brand)).thenReturn(listFound);
    when(ramServ.findByBrand("AMD")).thenReturn(listEmpty);
    when(ramServ.findBetweenPrice(100, 400)).thenReturn(listFound);
    when(ramServ.findBetweenPrice(200, 300)).thenReturn(listEmpty);
  }

  @AfterEach
  public void after(){
    
  }

  @Test
  public void notNullTest() throws Exception{
    assertThat(ramCont).isNotNull();
  }

  @Test
  public void getAllTest() throws Exception{
    mock.perform(get("/ram/all")).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  void getbyProductIdTest() throws Exception{
    mock.perform(
      get("/ram/id/{id}",ram.getProductId())).
      andExpect(status().isOk()).
      andExpect(jsonPath("$.brand",is(brand))).
      andExpect(jsonPath("$.modelNumber", is(modelNumber))).
      andExpect(jsonPath("$.title", is(title))).
      andExpect(jsonPath("$.price", is(price))).

      andExpect(jsonPath("$.caseLatency", is(ram.getCaseLatency()))).
      andExpect(jsonPath("$.voltage", is(ram.getVoltage()))).
      andExpect(jsonPath("$.multiChannelKit", is(ram.getMultiChannelKit()))).
      andExpect(jsonPath("$.timing", is(ram.getTiming())));
  }

  @Test
  public void getByBrandTest1() throws Exception{
    mock.perform(get("/ram/brand/{brand}",ram.getBrand())).
    andExpect(status().isOk()).
    andDo(print()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test
  public void getByBrandTest2() throws Exception{
    mock.perform(get("/ram/brand/{brand}","AMD")).
    andExpect(status().isOk());
  }

  @Test 
  public void getByPriceBetweenTest1() throws Exception{
    mock.perform(get("/ram/pricelow/{pricelow}/pricehigh/{pricehigh}",100,400)).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  public void getByPriceBetweenTest2() throws Exception{
    mock.perform(get("/ram/pricelow/{pricelow}/pricehigh/{pricehigh}",200,300)).
    andExpect(status().isOk());
  }

  @Test
  public void postRAMTest() throws Exception{
    mock.perform(post("/ram/add" ).
    contentType(MediaType.APPLICATION_JSON).
    content(new ObjectMapper().writeValueAsString(ram))).
    andExpect(status().isCreated());
  }
}
