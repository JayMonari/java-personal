package com.circuitcommerce.project2.serviceTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.model.SolidStateDrive;
import com.circuitcommerce.project2.repository.SolidStateDriveRepository;
import com.circuitcommerce.project2.service.SolidStateDriveService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SSDServiceTests {

  @Mock
  private SolidStateDriveRepository ssdRepo;
  @InjectMocks
  private SolidStateDriveService ssdServ;
  private SolidStateDrive ssd;
  private ArrayList<ProductShortDto> listfound;
  private ArrayList<ProductShortDto> listEmpty;

  private String maxSeqRead="Up to 560 MBps";
  private String maxSeqWrite="Up to 460 MBps";
  private String usedFor="";
  private String mttf="1,500,000 hours";
  private String kbRandomRead="Up to 74,000 IOPS";
  private String kb4RandomWrite="Up to 76,000 IOPS";
  private String controller="";

  private String brand="Mushkin Enhanced";
  private Long productId=(long)12345;
  private String modelNumber="MKNSSDRE1TB";
  private String title="Mushkin Enhanced Reactor 2.5\" 1TB SATA III MLC Internal Solid State Drive (SSD) MKNSSDRE1TB";
  private Double price=209.99;
  private ProductShortDto pos;

  @BeforeEach
  public void before(){
    ssd= new SolidStateDrive(maxSeqRead, maxSeqWrite, usedFor, mttf, kbRandomRead, kb4RandomWrite, controller);
    ssd.setBrand(brand);
    ssd.setProductId(productId);
    ssd.setModelNumber(modelNumber);
    ssd.setTitle(title);
    ssd.setPrice(price);

    pos=new ProductShortDto(productId, title, brand, price, null);

    listfound=new ArrayList<>();
    listEmpty=new ArrayList<>();
    listfound.add(pos);
    
    when(ssdRepo.getOne(productId)).thenReturn(ssd);
    when(ssdRepo.ssdSmall()).thenReturn(listfound);
    when(ssdRepo.findByBrand(brand)).thenReturn(listfound);
    when(ssdRepo.findByBrand("Western Digital")).thenReturn(listEmpty);
    when(ssdRepo.findByPriceBetween(200.0,300.0)).thenReturn(listfound);
    when(ssdRepo.findByPriceBetween(100,200.0)).thenReturn(listEmpty);

  }
  @AfterEach
  public void after(){

  }

  @Test
  public void NotNullTest(){
    assertThat(ssd).isNotNull();
  }

  @Test
  public void getAllTest() throws Exception{
    assertArrayEquals(ssdServ.getAllSolidStateDrives().toArray(), listfound.toArray());
  }

  @Test
  public void getOneTest() throws Exception{
    assertEquals(ssdServ.getSolidStateDriveById(productId), ssd);
  }

  @Test
  public void getCpuByBrandTest1() throws Exception{
    assertArrayEquals(ssdServ.findByBrand(brand).toArray(), listfound.toArray());
  }

  @Test
  public void getCpuByBrandTest2() throws Exception{
    assertArrayEquals(ssdServ.findByBrand("Western Digital").toArray(), listEmpty.toArray());
  }

  @Test
  public void getByPriceBetweenTest1() throws Exception{
    assertArrayEquals(ssdServ.findBetweenPrice(200.0,300.0).toArray(), listfound.toArray());
  }

  @Test
  public void getByPriceBetweenTest2() throws Exception{
    assertArrayEquals(ssdServ.findBetweenPrice(100,200.0).toArray(), listEmpty.toArray());
  }
}
