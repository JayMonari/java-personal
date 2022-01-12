package com.circuitcommerce.project2.controllerTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import com.circuitcommerce.project2.controller.SolidStateDriveController;
import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.dto.SolidStateDriveDto;
import com.circuitcommerce.project2.model.SolidStateDrive;
import com.circuitcommerce.project2.service.SolidStateDriveService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class SSDControllerTests {
  private MockMvc mock;

  @Mock
  private SolidStateDriveService ssdServ;
  @InjectMocks
  private SolidStateDriveController ssdCont;
  private SolidStateDrive ssd;
  private SolidStateDriveDto ssdDto;
  private ArrayList<ProductShortDto> listEmpty;
  private ArrayList<ProductShortDto> listFound;

  private String maxSeqRead = "Up to 560 MBps";
  private String maxSeqWrite = "Up to 460 MBps";
  private String usedFor = "";
  private String mttf = "1,500,000 hours";
  private String kbRandomRead = "Up to 74,000 IOPS";
  private String kb4RandomWrite = "Up to 76,000 IOPS";
  private String controller = "";

  private String brand = "Mushkin Enhanced";
  private Long productId = (long) 12345;
  private String modelNumber = "MKNSSDRE1TB";
  private String title = "Mushkin Enhanced Reactor 2.5\" 1TB SATA III MLC Internal Solid State Drive (SSD) MKNSSDRE1TB";
  private Double price = 209.99;
  private ProductShortDto pos;

  @BeforeEach
  public void before() {
    ssd = new SolidStateDrive(maxSeqRead, maxSeqWrite, usedFor, mttf, kbRandomRead, kb4RandomWrite, controller);
    ssd.setBrand(brand);
    ssd.setProductId(productId);
    ssd.setModelNumber(modelNumber);
    ssd.setTitle(title);
    ssd.setPrice(price);

    ssdDto = new SolidStateDriveDto(maxSeqRead, maxSeqWrite, usedFor, mttf, kbRandomRead, kb4RandomWrite, controller);
    ssdDto.setBrand(brand);
    ssdDto.setModelNumber(modelNumber);
    ssdDto.setTitle(title);
    ssdDto.setPrice(price);

    pos = new ProductShortDto(productId, title, brand, price, modelNumber);

    listEmpty = new ArrayList<>();
    listFound = new ArrayList<>();

    listFound.add(pos);

    mock=MockMvcBuilders.standaloneSetup(ssdCont).build();

    doNothing().when(ssdServ).insertSSD(ssdDto);
    when(ssdServ.getSolidStateDriveById(productId)).thenReturn(ssd);
    when(ssdServ.getAllSolidStateDrives()).thenReturn(listFound);
    when(ssdServ.findByBrand(brand)).thenReturn(listFound);
    when(ssdServ.findByBrand("Western Digital")).thenReturn(listEmpty);
    when(ssdServ.findBetweenPrice(150.0, 300.0)).thenReturn(listFound);
    when(ssdServ.findBetweenPrice(150.0, 200.0)).thenReturn(listEmpty);

  }

  @Test
  public void notNullTest() throws Exception {
    assertThat(ssdCont).isNotNull();
  }


  @Test
  public void getAllTest() throws Exception{
    mock.perform(get("/ssd/all")).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test
  public void getbyProductIdTest() throws Exception {
    mock.perform(
      get("/ssd/id/{id}",ssd.getProductId())).
      andExpect(status().isOk()).
      andExpect(jsonPath("$.brand",is(brand))).
      andExpect(jsonPath("$.modelNumber", is(modelNumber))).
      andExpect(jsonPath("$.title", is(title))).
      andExpect(jsonPath("$.price", is(ssd.getPrice()))).

      andExpect(jsonPath("$.maxSeqRead", is(ssd.getMaxSeqRead()))).
      andExpect(jsonPath("$.maxSeqWrite", is(ssd.getMaxSeqWrite()))).
      andExpect(jsonPath("$.usedFor", is(ssd.getUsedFor()))).
      andExpect(jsonPath("$.mttf", is(ssd.getMttf()))).
      andExpect(jsonPath("$.kb4RandomRead", is(ssd.getKb4RandomRead()))).
      andExpect(jsonPath("$.kb4RandomWrite", is(ssd.getKb4RandomWrite()))).
      andExpect(jsonPath("$.controller", is(ssd.getController())));
  }

  @Test
  public void getByBrandTest1() throws Exception{
    mock.perform(get("/ssd/brand/{brand}",ssd.getBrand())).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test
  public void getByBrandTest2() throws Exception{
    mock.perform(get("/ssd/brand/{brand}","Western Digital")).
    andExpect(status().isOk());
  }

  @Test
  public void getByPriceBetweenTest1() throws Exception{
    mock.perform(get("/ssd/pricelow/{pricelow}/pricehigh/{pricehigh}",150,300)).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test
  public void postSSDTest() throws Exception{
    mock.perform(post("/ssd/add" ).
    contentType(MediaType.APPLICATION_JSON).
    content(new ObjectMapper().writeValueAsString(ssd))).
    andExpect(status().isCreated());
  }
}
