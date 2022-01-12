package com.circuitcommerce.project2.serviceTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.model.RandomAccessMemory;
import com.circuitcommerce.project2.repository.RandomAccessMemoryRepository;
import com.circuitcommerce.project2.service.RandomAccessMemoryService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RAMServiceTest {
  @Mock
  private RandomAccessMemoryRepository ramRepo;
  @InjectMocks
  private RandomAccessMemoryService ramServ;
  private RandomAccessMemory ram;
  private ArrayList<ProductShortDto> listfound;
  private ArrayList<ProductShortDto> listEmpty;

  private Integer caseLatency=10;
  private String voltage="1.5V";
  private String multiChannelKit="Quad Channel Kit";
  private String timing="10-11-10-30";

  private String brand="G.SKILL";
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

    pos=new ProductShortDto(productId, title, brand, price, null);

    listfound=new ArrayList<>();
    listEmpty=new ArrayList<>();
    listfound.add(pos);

    when(ramRepo.getOne(productId)).thenReturn(ram);
    when(ramRepo.ramSmall()).thenReturn(listfound);
    when(ramRepo.findByBrand(brand)).thenReturn(listfound);
    when(ramRepo.findByBrand("Corsair")).thenReturn(listEmpty);
    when(ramRepo.findByPriceBetween(120, 150)).thenReturn(listfound);
    when(ramRepo.findByPriceBetween(100, 120)).thenReturn(listEmpty);
  }
  @AfterEach
  public void after(){

  }

  @Test
  public void NotNullTest(){
    assertThat(ramServ).isNotNull();
  }

  @Test
  public void getOneRAMTest() throws Exception{
    assertEquals(ramServ.getRandomAccessMemoryById(productId), ram);
  }

  @Test
  public void getAllRamTest() throws Exception{
    assertArrayEquals(ramServ.getAllRandomAccessMemories().toArray(), listfound.toArray());
  }

  @Test
  public void getByBrandTest1()throws Exception{
    assertArrayEquals(ramServ.findByBrand(brand).toArray(),listfound.toArray());
  }

  @Test
  public void getByBrandTest2() throws Exception{
    assertArrayEquals(ramServ.findByBrand("Corsair").toArray(), listEmpty.toArray());
  }

  @Test
  public void getPriceBetweenTest1() throws Exception{
    assertArrayEquals(ramServ.findBetweenPrice(120, 150).toArray(),listfound.toArray());
  }

  @Test
  public void getPriceBetweenTest2() throws Exception{
    assertArrayEquals(ramServ.findBetweenPrice(100, 120).toArray(),listEmpty.toArray());
  }

}
