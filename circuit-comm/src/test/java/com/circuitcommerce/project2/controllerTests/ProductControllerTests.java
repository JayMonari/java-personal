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

import com.circuitcommerce.project2.controller.ProductController;
import com.circuitcommerce.project2.dto.ProductDto;
import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.model.Product;
import com.circuitcommerce.project2.service.ProductService;
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
public class ProductControllerTests {

  private MockMvc mock;

  @Mock
  private ProductService proServ;
  @InjectMocks
  private ProductController proCont;
  private Product product;
  private ProductDto productDto;
  private ArrayList<ProductShortDto> listEmpty;
  private ArrayList<ProductShortDto> listFound;

  private String brand="CoolerMaster";
  private Long productId=(long)12345;
  private String modelNumber="SGC-5000-KWN1";
  private String title="CM Storm Trooper - Gaming Full Tower Computer Case with Handle and External 2.5\" Drive Dock with Side Panel Window";
  private Double price=164.99;
  private ProductShortDto pos;
  

  @BeforeEach
  public void before(){
    product=new Product(productId, modelNumber, title, title, brand, price, null, null, null);

    productDto=new ProductDto(modelNumber, title, title, brand, price, null, null, null);

    pos=new ProductShortDto(productId, title, brand, price, modelNumber);

    listEmpty=new ArrayList<>();
    listFound=new ArrayList<>();

    listFound.add(pos);

    mock=MockMvcBuilders.standaloneSetup(proCont).build();

    doNothing().when(proServ).insertProduct(productDto);
    when(proServ.getProductByProductId(productId)).thenReturn(product);
    when(proServ.getAllProducts()).thenReturn(listFound);
    when(proServ.findByBrand(brand)).thenReturn(listFound);
    when(proServ.findByBrand("AMD")).thenReturn(listEmpty);
    when(proServ.findBetweenPrice(150, 400)).thenReturn(listFound);
    when(proServ.findBetweenPrice(200, 300)).thenReturn(listEmpty);
  }

  @AfterEach
  public void after(){

  }

  @Test
  public void notNullTest() throws Exception{
    assertThat(proCont).isNotNull();
  }

  @Test
  public void getAllTest() throws Exception{
    mock.perform(get("/product/all")).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  void getbyProductIdTest() throws Exception{
    mock.perform(
      get("/product/id/{id}",product.getProductId())).
      andExpect(status().isOk()).
      andExpect(jsonPath("$.brand",is(brand))).
      andExpect(jsonPath("$.modelNumber", is(modelNumber))).
      andExpect(jsonPath("$.title", is(title))).
      andExpect(jsonPath("$.price", is(price)));
  }

  @Test
  public void getByBrandTest1() throws Exception{
    mock.perform(get("/product/all/brand/{brand}",product.getBrand())).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test
  public void getByBrandTest2() throws Exception{
    mock.perform(get("/product/all/brand/{brand}","AMD")).
    andExpect(status().isOk());
  }

  @Test 
  public void getByPriceBetweenTest1() throws Exception{
    mock.perform(get("/product/all/pricelow/{pricelow}/pricehigh/{pricehigh}",150,400)).
    andExpect(status().isOk()).
    andExpect(jsonPath("$[0].brand",is(brand))).
    andExpect(jsonPath("$[0].modelNumber", is(modelNumber))).
    andExpect(jsonPath("$[0].abbreviatedTitle", is(title))).
    andExpect(jsonPath("$[0].price", is(price)));
  }

  @Test 
  public void getByPriceBetweenTest2() throws Exception{
    mock.perform(get("/product/all/pricelow/{pricelow}/pricehigh/{pricehigh}",200,300)).
    andExpect(status().isOk());
  }

  @Test
  public void postProductTest() throws Exception{
    mock.perform(post("/product/add" ).
    contentType(MediaType.APPLICATION_JSON).
    content(new ObjectMapper().writeValueAsString(product))).
    andExpect(status().isCreated());
  }
}
