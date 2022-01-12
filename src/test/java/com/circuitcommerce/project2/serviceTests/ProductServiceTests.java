package com.circuitcommerce.project2.serviceTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.model.Product;
import com.circuitcommerce.project2.repository.ProductRepository;
import com.circuitcommerce.project2.service.ProductService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceTests {

  @Mock
  private ProductRepository proRepo;
  @InjectMocks
  private ProductService proServ;
  private Product product;
  private ArrayList<Product> listfound;
  private ArrayList<ProductShortDto> listEmpty;
  private ArrayList<ProductShortDto> listofpos;
  
  private String brand="CoolerMaster";
  private Long productId=(long)12345;
  private String modelNumber="SGC-5000-KWN1";
  private String title="CM Storm Trooper - Gaming Full Tower Computer Case with Handle and External 2.5\" Drive Dock with Side Panel Window";
  private String description="My(Matthew Sheldon) computer case. It's awesome. Has been compared to storm trooper armor. It's a Beast of a case.";
  private Double price=164.99;
  private ProductShortDto pos; 

  @BeforeEach
  public void before(){
    
    product=new Product(productId, modelNumber, title, brand, description, price, null, null, null );
    pos=new ProductShortDto(productId, title, brand, price, null); 

    listfound=new ArrayList<>();
    listEmpty=new ArrayList<>();
    listofpos=new ArrayList<>();

    listofpos.add(pos);
    listfound.add(product);
    
    when(proRepo.getOne(productId)).thenReturn(product);
    when(proRepo.findAll()).thenReturn(listfound);
    when(proRepo.findByBrand(brand)).thenReturn(listofpos);
    when(proRepo.findByBrand("Compuserv")).thenReturn(listEmpty);
    when(proRepo.findByPriceBetween(150.0, 180.1)).thenReturn(listofpos);
    when(proRepo.findByPriceBetween(200.0, 250.0)).thenReturn(listEmpty);
  }
  @AfterEach
  public void after(){

  }

  @Test
  public void NotNullTest(){
    assertThat(proServ).isNotNull();
  }

  @Test
  public void getAllProductsTest(){
    assertArrayEquals(proServ.getAllProducts().toArray(), listfound.toArray());
  }

  @Test
  public void getProductByIdTest() throws Exception{
    assertEquals(proServ.getProductByProductId(productId), product);
  }
  
  @Test
  public void getProductsByBrandTest1() throws Exception{
    assertArrayEquals(proServ.findByBrand(brand).toArray(),listfound.toArray());
  }

  @Test
  public void getProductsByBrandTest2() throws Exception{
    assertArrayEquals(proServ.findByBrand("Compuserv").toArray(),listEmpty.toArray());
  }

  @Test
  public void getProductByPriceBetweenTest1() throws Exception{
    assertArrayEquals(proServ.findBetweenPrice(150.0, 180.1).toArray(),listfound.toArray());
  }

  @Test
  public void getProductByPriceBetweenTest2() throws Exception{
    assertArrayEquals(proServ.findBetweenPrice(200.0, 250.0).toArray(),listEmpty.toArray());
  }
}
