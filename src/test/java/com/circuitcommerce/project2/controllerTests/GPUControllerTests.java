package com.circuitcommerce.project2.controllerTests;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import com.circuitcommerce.project2.controller.VideoCardController;
import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.dto.VideoCardDto;
import com.circuitcommerce.project2.model.VideoCard;
import com.circuitcommerce.project2.service.VideoCardService;
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
public class GPUControllerTests {
  private MockMvc mock;

  @Mock
  private VideoCardService gpuServ;
  @InjectMocks
  private VideoCardController gpuCont;
  private VideoCard gpu;
  private VideoCardDto gpuDto;
  private ArrayList<ProductShortDto> listEmpty;
  private ArrayList<ProductShortDto> listFound;

  private String coreClock="1140MHz";
  private String maxResolution="4096x2160";
  private String displayPort="1xDisplayPort 1.2";
  private String dvi="1xDual-link DVI-I 1xDual-link DVI-D";
  private String hdmi="1 x HDMI 2.0";
  private String cardDimensions="10.91\" x 5.51\"";

  private String brand="MSI";
  private Long productId=(long)12345;
  private String modelNumber="GTX 970 GAMING 4G";
  private String title="MSI GeForce GTX 970 GAMING 4G";
  private Double price=345.55;
  private ProductShortDto pos;


  @BeforeEach
  public void before(){
    gpu=new VideoCard(coreClock, maxResolution, displayPort, dvi, hdmi, cardDimensions);
    gpu.setBrand(brand);
    gpu.setProductId(productId);
    gpu.setModelNumber(modelNumber);
    gpu.setTitle(title);
    gpu.setPrice(price);

    gpuDto=new VideoCardDto(coreClock, maxResolution, displayPort, dvi, hdmi, cardDimensions);
    gpuDto.setBrand(brand);
    gpuDto.setModelNumber(modelNumber);
    gpuDto.setTitle(title);
    gpuDto.setPrice(price);

    pos=new ProductShortDto(productId, title, brand, price, modelNumber);

    listEmpty=new ArrayList<>();
    listFound=new ArrayList<>();

    listFound.add(pos);

    mock=MockMvcBuilders.standaloneSetup(gpuCont).build();

    doNothing().when(gpuServ).insertVideoCard(gpuDto);
    when(gpuServ.getVideoCardById(productId)).thenReturn(gpu);
    when(gpuServ.getAllVideoCards()).thenReturn(listFound);
    when(gpuServ.findByBrand(brand)).thenReturn(listFound);
    when(gpuServ.findByBrand("Gigabyte")).thenReturn(listEmpty);
    when(gpuServ.findBetweenPrice(300.0, 400.0)).thenReturn(listFound);
    when(gpuServ.findBetweenPrice(150.0, 200.0)).thenReturn(listEmpty);
  }

  @AfterEach
  public void after(){

  }

  @Test
  public void notNullTest() throws Exception{
    assertThat(gpuCont).isNotNull();
  }

  @Test
  public void getAllTest() throws Exception{
    mock.perform(get("/videocard/all")).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  void getbyProductIdTest() throws Exception{
    mock.perform(
      get("/videocard/id/{id}",gpu.getProductId())).
      andExpect(status().isOk()).
      andExpect(jsonPath("$.brand",is(brand))).
      andExpect(jsonPath("$.modelNumber", is(modelNumber))).
      andExpect(jsonPath("$.title", is(title))).
      andExpect(jsonPath("$.price", is(price))).

      andExpect(jsonPath("$.coreClock", is(gpu.getCoreClock()))).
      andExpect(jsonPath("$.maxResolution", is(gpu.getMaxResolution()))).
      andExpect(jsonPath("$.displayPort", is(gpu.getDisplayPort()))).
      andExpect(jsonPath("$.dvi", is(gpu.getDvi()))).
      andExpect(jsonPath("$.hdmi", is(gpu.getHdmi()))).
      andExpect(jsonPath("$.cardDimensions", is(gpu.getCardDimensions())));
  }

  @Test
  public void getByBrandTest1() throws Exception{
    mock.perform(get("/videocard/brand/{brand}",gpu.getBrand())).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test
  public void getByBrandTest2() throws Exception{
    mock.perform(get("/videocard/brand/{brand}","Gigabyte")).
    andExpect(status().isOk());
  }

  @Test 
  public void getByPriceBetweenTest1() throws Exception{
    mock.perform(get("/videocard/pricelow/{pricelow}/pricehigh/{pricehigh}",300,400)).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  public void getByPriceBetweenTest2() throws Exception{
    mock.perform(get("/videocard/pricelow/{pricelow}/pricehigh/{pricehigh}",150,200)).
    andExpect(status().isOk());
  }

  @Test
  public void postGPUTest() throws Exception{
    mock.perform(post("/videocard/add" ).
    contentType(MediaType.APPLICATION_JSON).
    content(new ObjectMapper().writeValueAsString(gpu))).
    andExpect(status().isCreated());
  }
  
}
