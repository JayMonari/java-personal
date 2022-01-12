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

import com.circuitcommerce.project2.controller.MotherBoardController;
import com.circuitcommerce.project2.dto.MotherBoardDto;
import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.model.MotherBoard;
import com.circuitcommerce.project2.service.MotherBoardService;
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
public class MotherboardControllerTests {

  private MockMvc mock;

  @Mock
  private MotherBoardService moboServ;
  @InjectMocks
  private MotherBoardController moboCont;
  private MotherBoard mobo;
  private MotherBoardDto moboDto;
  
  private ArrayList<ProductShortDto> listEmpty;
  private ArrayList<ProductShortDto> listFound;

  private String memoryStandard="DDR3 3300*(*OC)/ 3200*/ 3100*/ 3000*/ 2800*/ 2666*/ 2600*/ 2400*/ 2200*/ 2133*/ 2000*/ 1866*/ 1600/ 1333/ 1066";
  private String numOfMemorySlots="4×240pin";
  private String audioChipset="Realtek ALC1150";
  private String onboardVideoChipset="Supported only by CPU with integrated graphic";
  private String pciExpress="4 x PCIe 2.0 x1 slots";

  private String brand="MSI";
  private Long productId=(long)12345;
  private String modelNumber="Z97 GAMING 5";
  private String title="MSI MSI Gaming Z97 GAMING 5 LGA 1150 Intel Z97 HDMI SATA 6Gb/s USB 3.0 ATX Intel Motherboard";
  private Double price=149.99;
  private ProductShortDto pos;


  @BeforeEach
  public void before(){
    mobo =new MotherBoard(memoryStandard, numOfMemorySlots, audioChipset, onboardVideoChipset, pciExpress);
    mobo.setBrand(brand);
    mobo.setProductId(productId);
    mobo.setModelNumber(modelNumber);
    mobo.setTitle(title);
    mobo.setPrice(price);

    moboDto=new MotherBoardDto(memoryStandard, numOfMemorySlots, audioChipset, onboardVideoChipset, pciExpress);
    moboDto.setBrand(brand);
    moboDto.setModelNumber(modelNumber);
    moboDto.setTitle(title);
    moboDto.setPrice(price);

    pos=new ProductShortDto(productId, title, brand, price, modelNumber);

    listEmpty=new ArrayList<>();
    listFound=new ArrayList<>();

    listFound.add(pos);

    mock=MockMvcBuilders.standaloneSetup(moboCont).build();

    doNothing().when(moboServ).insertMotherBoard(moboDto);
    when(moboServ.getMotherboardById(productId)).thenReturn(mobo);
    when(moboServ.getAllMotherBoards()).thenReturn(listFound);
    when(moboServ.findByBrand(brand)).thenReturn(listFound);
    when(moboServ.findByBrand("Gigabyte")).thenReturn(listEmpty);
    when(moboServ.findBetweenPrice(100, 400)).thenReturn(listFound);
    when(moboServ.findBetweenPrice(200, 300)).thenReturn(listEmpty);
  }

  @AfterEach
  public void after(){

  }

  @Test
  public void notNullTest() throws Exception{
    assertThat(moboCont).isNotNull();
  }

  @Test
  public void getAllTest() throws Exception{
    mock.perform(get("/motherboard/all")).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  void getbyProductIdTest() throws Exception{
    mock.perform(
      get("/motherboard/id/{id}",mobo.getProductId())).
      andExpect(status().isOk()).
      andExpect(jsonPath("$.brand",is(brand))).
      andExpect(jsonPath("$.modelNumber", is(modelNumber))).
      andExpect(jsonPath("$.title", is(title))).
      andExpect(jsonPath("$.price", is(price))).

      andExpect(jsonPath("$.memoryStandard", is(mobo.getMemoryStandard()))).
      andExpect(jsonPath("$.numOfMemorySlots", is(mobo.getNumOfMemorySlots()))).
      andExpect(jsonPath("$.audioChipset", is(mobo.getAudioChipset()))).
      andExpect(jsonPath("$.onboardVideoChipset", is(mobo.getOnboardVideoChipset()))).
      andExpect(jsonPath("$.pciExpress", is(mobo.getPciExpress())));
  }

  @Test
  public void getByBrandTest1() throws Exception{
    mock.perform(get("/motherboard/brand/{brand}",mobo.getBrand())).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test
  public void getByBrandTest2() throws Exception{
    mock.perform(get("/motherboard/brand/{brand}","Gigabyte")).
    andExpect(status().isOk());
  }

  @Test 
  public void getByPriceBetweenTest1() throws Exception{
    mock.perform(get("/motherboard/pricelow/{pricelow}/pricehigh/{pricehigh}",100,400)).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  public void getByPriceBetweenTest2() throws Exception{
    mock.perform(get("/motherboard/pricelow/{pricelow}/pricehigh/{pricehigh}",200,300)).
    andExpect(status().isOk());
  }

  @Test
  public void postMotherboard() throws Exception{
    mock.perform(post("/motherboard/add" ).
    contentType(MediaType.APPLICATION_JSON).
    content(new ObjectMapper().writeValueAsString(mobo))).
    andExpect(status().isCreated());
  }
  
}
