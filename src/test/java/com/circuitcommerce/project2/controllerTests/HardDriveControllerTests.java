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

import com.circuitcommerce.project2.controller.HardDiskDriveController;
import com.circuitcommerce.project2.dto.HardDiskDriveDto;
import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.model.HardDiskDrive;
import com.circuitcommerce.project2.service.HardDiskDriveService;
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
public class HardDriveControllerTests {

  private MockMvc mock;

  @Mock
  private HardDiskDriveService hddServ;
  @InjectMocks
  private HardDiskDriveController hddCont;
  private HardDiskDrive hdd;
  private HardDiskDriveDto hddDto;
  private ArrayList<ProductShortDto> listEmpty;
  private ArrayList<ProductShortDto> listFound;

  private Double height=10.0;
  private Double width=10.0;
  private Double length=10.0;
  private Double weight=10.0;
  private String packageContents="Bare Drive";
  private Double averageLatency=null;

  private String brand="Western Digital";
  private Long productId=(long)12345;
  private String modelNumber="WD10EURX";
  private String title="Western Digital AV-GP WD10EURX 1TB IntelliPower 64MB Cache SATA 6.0Gb/s 3.5\" Internal Hard Drive -Manufacture Recertified Bare Drive";
  private Double price=36.99;
  private ProductShortDto pos;


  @BeforeEach
  public void before(){
    hdd=new HardDiskDrive(height, width, length, weight, packageContents, averageLatency);
    hdd.setBrand(brand);
    hdd.setProductId(productId);
    hdd.setModelNumber(modelNumber);
    hdd.setTitle(title);
    hdd.setPrice(price);

    hddDto= new HardDiskDriveDto(height, width, length, weight, packageContents, 0);
    hddDto.setBrand(brand);
    hddDto.setModelNumber(modelNumber);
    hddDto.setTitle(title);
    hddDto.setPrice(price);

    pos=new ProductShortDto(productId, title, brand, price, modelNumber);

    listEmpty=new ArrayList<>();
    listFound=new ArrayList<>();

    listFound.add(pos);

    mock=MockMvcBuilders.standaloneSetup(hddCont).build();

    doNothing().when(hddServ).insertHDD(hddDto);
    when(hddServ.getHardDiskDriveById(productId)).thenReturn(hdd);
    when(hddServ.getAllHdds()).thenReturn(listFound);
    when(hddServ.findByBrand(brand)).thenReturn(listFound);
    when(hddServ.findByBrand("Seagate Barracuda")).thenReturn(listEmpty);
    when(hddServ.findBetweenPrice(30, 40)).thenReturn(listFound);
    when(hddServ.findBetweenPrice(20, 30)).thenReturn(listEmpty);
  }

  @AfterEach
  public void after(){

  }

  @Test
  public void notNullTest() throws Exception{
    assertThat(hddCont).isNotNull();
  }

  @Test
  public void getAllTest() throws Exception{
    mock.perform(get("/hdd/all")).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  void getbyProductIdTest() throws Exception{
    mock.perform(
      get("/hdd/id/{id}",hdd.getProductId())).
      andExpect(status().isOk()).
      andExpect(jsonPath("$.brand",is(brand))).
      andExpect(jsonPath("$.modelNumber", is(modelNumber))).
      andExpect(jsonPath("$.title", is(title))).
      andExpect(jsonPath("$.price", is(price))).

      andExpect(jsonPath("$.height", is(hdd.getHeight()))).
      andExpect(jsonPath("$.width", is(hdd.getWidth()))).
      andExpect(jsonPath("$.length", is(hdd.getLength()))).
      andExpect(jsonPath("$.weight", is(hdd.getWeight()))).
      andExpect(jsonPath("$.packageContents", is(hdd.getPackageContents()))).
      andExpect(jsonPath("$.averageLatency", is(hdd.getAverageLatency())));
  }

  @Test
  public void getByBrandTest1() throws Exception{
    mock.perform(get("/hdd/brand/{brand}",hdd.getBrand())).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test
  public void getByBrandTest2() throws Exception{
    mock.perform(get("/hdd/brand/{brand}","Seagate Barracuda")).
    andExpect(status().isOk());
  }

  @Test 
  public void getByPriceBetweenTest1() throws Exception{
    mock.perform(get("/hdd/pricelow/{pricelow}/pricehigh/{pricehigh}",30,40)).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  public void getByPriceBetweenTest2() throws Exception{
    mock.perform(get("/hdd/pricelow/{pricelow}/pricehigh/{pricehigh}",20,30)).
    andExpect(status().isOk());
  }

  @Test
  public void postHardDriveTest() throws Exception{
    mock.perform(post("/hdd/add" ).
    contentType(MediaType.APPLICATION_JSON).
    content(new ObjectMapper().writeValueAsString(hdd))).
    andExpect(status().isCreated());
  }
}
