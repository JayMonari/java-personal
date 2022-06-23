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

import com.circuitcommerce.project2.controller.PowerSupplyController;
import com.circuitcommerce.project2.dto.PowerSupplyDto;
import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.model.PowerSupply;
import com.circuitcommerce.project2.service.PowerSupplyService;
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
public class PowerSupplyControllerTests {

  private MockMvc mock;

  @Mock
  private PowerSupplyService powerServ;
  @InjectMocks
  private PowerSupplyController powerCont;
  private PowerSupply power;
  private PowerSupplyDto powerDto;
  private ArrayList<ProductShortDto> listEmpty;
  private ArrayList<ProductShortDto> listFound;
  
  private String fans="1 x 140mm Double Ball Bearing Fan";
  private String mainConnector="	24Pin";
  private String rails="Single";
  private String pciExpressConnector="6 x 6+2-Pin, 2 x 6-Pin";

  private String brand="EVGA";
  private Long productId=(long)12345;
  private String modelNumber="120-G2-1000-XR";
  private String title="EVGA SuperNOVA 1000 G2 120-G2-1000-XR 80+ GOLD 1000W Fully Modular Includes FREE Power On Self Tester Power Supply";
  private Double price=169.99;
  private ProductShortDto pos;
  


  @BeforeEach
  public void before(){
    power=new PowerSupply(fans, mainConnector, rails, pciExpressConnector);
    power.setBrand(brand);
    power.setProductId(productId);
    power.setModelNumber(modelNumber);
    power.setTitle(title);
    power.setPrice(price);

    powerDto=new PowerSupplyDto(fans, mainConnector, rails, pciExpressConnector);
    powerDto.setBrand(brand);
    powerDto.setModelNumber(modelNumber);
    powerDto.setTitle(title);
    powerDto.setPrice(price);

    pos=new ProductShortDto(productId, title, brand, price, modelNumber);

    listEmpty=new ArrayList<>();
    listFound=new ArrayList<>();

    listFound.add(pos);

    mock=MockMvcBuilders.standaloneSetup(powerCont).build();

    doNothing().when(powerServ).insertPowerSupply(powerDto);
    when(powerServ.getPowerSupplyById(productId)).thenReturn(power);
    when(powerServ.getAllPowerSupplies()).thenReturn(listFound);
    when(powerServ.findByBrand(brand)).thenReturn(listFound);
    when(powerServ.findByBrand("Corsair")).thenReturn(listEmpty);
    when(powerServ.findBetweenPrice(100, 400)).thenReturn(listFound);
    when(powerServ.findBetweenPrice(200, 300)).thenReturn(listEmpty);
  }

  @AfterEach
  public void after(){

  }

  @Test
  public void notNullTest() throws Exception{
    assertThat(powerCont).isNotNull();
  }

  @Test
  public void getAllTest() throws Exception{
    mock.perform(get("/powersupply/all")).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  void getbyProductIdTest() throws Exception{
    mock.perform(
      get("/powersupply/id/{id}",power.getProductId())).
      andExpect(status().isOk()).
      andExpect(jsonPath("$.brand",is(brand))).
      andExpect(jsonPath("$.modelNumber", is(modelNumber))).
      andExpect(jsonPath("$.title", is(title))).
      andExpect(jsonPath("$.price", is(price))).

      andExpect(jsonPath("$.fans", is(power.getFans()))).
      andExpect(jsonPath("$.mainConnector", is(power.getMainConnector()))).
      andExpect(jsonPath("$.rails", is(power.getRails()))).
      andExpect(jsonPath("$.pciExpressConnector", is(power.getPciExpressConnector())));
  }

  @Test
  public void getByBrandTest1() throws Exception{
    mock.perform(get("/powersupply/brand/{brand}",power.getBrand())).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test
  public void getByBrandTest2() throws Exception{
    mock.perform(get("/powersupply/brand/{brand}","Corsair")).
    andExpect(status().isOk());
  }

  @Test 
  public void getByPriceBetweenTest1() throws Exception{
    mock.perform(get("/powersupply/pricelow/{pricelow}/pricehigh/{pricehigh}",100,400)).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  public void getByPriceBetweenTest2() throws Exception{
    mock.perform(get("/powersupply/pricelow/{pricelow}/pricehigh/{pricehigh}",200,300)).
    andExpect(status().isOk());
  }

  @Test
  public void postMotherBoard() throws Exception{
    mock.perform(post("/powersupply/add" ).
    contentType(MediaType.APPLICATION_JSON).
    content(new ObjectMapper().writeValueAsString(power))).
    andExpect(status().isCreated());
  }
}
