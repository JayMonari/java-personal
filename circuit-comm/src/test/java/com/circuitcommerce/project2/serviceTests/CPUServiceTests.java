package com.circuitcommerce.project2.serviceTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.model.CentralProcessingUnit;
import com.circuitcommerce.project2.repository.CentralProcessingUnitRepository;
import com.circuitcommerce.project2.service.CentralProcessingUnitService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CPUServiceTests {
  @Mock
  private CentralProcessingUnitRepository mockCpuRepo;
  @InjectMocks
  private CentralProcessingUnitService cpuServ;
  private CentralProcessingUnit cpu;
  private ArrayList<ProductShortDto> listEmpty;
  private ArrayList<ProductShortDto> listofpos;

  private String series="Core i7 4th Gen";
  private String l3Cache="8MB";
  private String l2Cache="4x256KB";
  private String coolingDevice="";
  private String manufacturingTech="22nm";


  private String brand="Intel";
  private Long productId=(long)12345;
  private String modelNumber="BX80646I74790K";
  private String title="Intel Core i7-4790K Devil's Canyon Quad-Core 4.0 GHz LGA 1150 88W BX80646I74790K Desktop Processor Intel HD Graphics 4600";
  private Double price=339.99;
  private ProductShortDto pos;


  @BeforeEach
  public  void before(){
    cpu=new CentralProcessingUnit(series, l3Cache, l2Cache, coolingDevice, manufacturingTech);
    cpu.setBrand(brand);
    cpu.setProductId(productId);
    cpu.setModelNumber(modelNumber);
    cpu.setTitle(title);
    cpu.setPrice(price);

    pos=new ProductShortDto(productId, title, brand, price, null);

    listEmpty=new ArrayList<>();
    listofpos=new ArrayList<>();
    listofpos.add(pos);

    when(mockCpuRepo.getOne((long)12345)).thenReturn(cpu);
    when(mockCpuRepo.cpuSmall()).thenReturn(listofpos);
    when(mockCpuRepo.findByBrand(brand)).thenReturn(listofpos);
    when(mockCpuRepo.findByBrand("AMD")).thenReturn(listEmpty);
    when(mockCpuRepo.findByPriceBetween(300.00,400.00)).thenReturn(listofpos);
    when(mockCpuRepo.findByPriceBetween(190.00,200.00)).thenReturn(listEmpty);
  }
  @AfterEach
  public void after(){

  }
  @Test
  public void NotNullTest(){
    assertThat(cpuServ).isNotNull();
  }

  @Test
  public void TestGetAll(){
    assertArrayEquals(listofpos.toArray(), cpuServ.getAllCpus().toArray());
  }
  @Test
  public void getCpuByIdTest() throws Exception{
    CentralProcessingUnit test=cpuServ.getCpuById((long)12345);
    assertEquals(cpu,test);
  }
  @Test
  public void getCpuByBrandTest1() throws Exception{
    assertArrayEquals(cpuServ.findByBrand(brand).toArray(), listofpos.toArray());
  }
  @Test
  public void getCpuByBrandTest2() throws Exception{
    assertArrayEquals(cpuServ.findByBrand("AMD").toArray(), listEmpty.toArray());
  }
  @Test
  public void getCpuBetweenPriceTest1() throws Exception{
    assertArrayEquals(cpuServ.findBetweenPrice(300.00,400.00).toArray(), listofpos.toArray());
  }
  @Test
  public void getCpuBetweenPriceTest2() throws Exception{
    assertArrayEquals(cpuServ.findBetweenPrice(190.00,200.00).toArray(), listEmpty.toArray());
  }
}
