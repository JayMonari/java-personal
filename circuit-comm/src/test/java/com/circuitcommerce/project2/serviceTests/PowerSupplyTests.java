package com.circuitcommerce.project2.serviceTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.model.PowerSupply;
import com.circuitcommerce.project2.repository.PowerSupplyRepository;
import com.circuitcommerce.project2.service.PowerSupplyService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PowerSupplyTests {
  @Mock
  private PowerSupplyRepository powerRepo;
  @InjectMocks
  private PowerSupplyService powerServ;
  private PowerSupply power;
  private ArrayList<ProductShortDto> listfound;
  private ArrayList<ProductShortDto> listEmpty;

  private String fans="1 x 140mm Double Ball Bearing Fan";
  private String mainConnector="	24Pin";
  private String rails="Single";
  private String pciEcpressConnector="6 x 6+2-Pin, 2 x 6-Pin";

  private String brand="EVGA";
  private Long productId=(long)12345;
  private String modelNumber="120-G2-1000-XR";
  private String title="EVGA SuperNOVA 1000 G2 120-G2-1000-XR 80+ GOLD 1000W Fully Modular Includes FREE Power On Self Tester Power Supply";
  private Double price=169.99;
  private ProductShortDto pos;


  @BeforeEach
  public void before(){
    power=new PowerSupply(fans, mainConnector, rails, pciEcpressConnector);
    power.setBrand(brand);
    power.setProductId(productId);
    power.setModelNumber(modelNumber);
    power.setTitle(title);
    power.setPrice(price);

    pos=new ProductShortDto(productId, title, brand, price, null);

    listfound=new ArrayList<>();
    listEmpty=new ArrayList<>();
    listfound.add(pos);

    when(powerRepo.getOne(productId)).thenReturn(power);
    when(powerRepo.psSmall()).thenReturn(listfound);
    when(powerRepo.findByBrand(brand)).thenReturn(listfound);
    when(powerRepo.findByBrand("Corsair")).thenReturn(listEmpty);
    when(powerRepo.findByPriceBetween(150.0,400.00)).thenReturn(listfound);
    when(powerRepo.findByPriceBetween(190.00,200.00)).thenReturn(listEmpty);
  }
  @AfterEach
  public void after(){

  }

  @Test
  public void NotNullTest(){
    assertThat(powerServ).isNotNull();
  }

  @Test
  public void getAllProductsTest() throws Exception{
    assertArrayEquals(powerServ.getAllPowerSupplies().toArray(),listfound.toArray());
  }

  @Test
  public void getPowerSupplyByIdTest()throws Exception{
    assertEquals(powerServ.getPowerSupplyById(productId), power);
  }

  @Test
  public void getPowerSupplyByBrandTest1() throws Exception{
    assertArrayEquals(powerServ.findByBrand(brand).toArray(),listfound.toArray());
  }

  @Test
  public void getPowerSupplyByBrandTest2() throws Exception{
    assertArrayEquals(powerServ.findByBrand("Corsair").toArray(),listEmpty.toArray());
  }

  @Test
  public void getPowerSupplyBetweenPriceTest1()throws Exception{
    assertArrayEquals(powerServ.findBetweenPrice(150.0,400.00).toArray(),listfound.toArray());
  }

  @Test
  public void getPowerSupplyBetweenPriceTest2()throws Exception{
    assertArrayEquals(powerServ.findBetweenPrice(190.00,200.00).toArray(),listEmpty.toArray());
  }
  

}
