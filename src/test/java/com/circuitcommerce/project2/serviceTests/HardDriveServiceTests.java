package com.circuitcommerce.project2.serviceTests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.model.HardDiskDrive;
import com.circuitcommerce.project2.repository.HardDiskDriveRepository;
import com.circuitcommerce.project2.service.HardDiskDriveService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HardDriveServiceTests {
  @Mock
  private HardDiskDriveRepository hddRepo;
  @InjectMocks
  private HardDiskDriveService diskServ;

  private HardDiskDrive hdd;
  private ArrayList<ProductShortDto> listfound;
  private ArrayList<ProductShortDto> listEmpty;

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

    pos=new ProductShortDto(productId, title, brand, price, null);

    listfound.add(pos);
    listfound=new ArrayList<>();
    listEmpty=new ArrayList<>();

    when(hddRepo.getOne(productId)).thenReturn(hdd);
    when(hddRepo.hddSmall()).thenReturn(listfound);
    when(hddRepo.findByBrand(brand)).thenReturn(listfound);
    when(hddRepo.findByBrand("Seagate Barracuda")).thenReturn(listEmpty);
    when(hddRepo.findByPriceBetween(20.0,40.0)).thenReturn(listfound);
    when(hddRepo.findByPriceBetween(40.0,50.0)).thenReturn(listEmpty);

  }
  @AfterEach
  public void after(){

  }

  @Test
  public void NotNullTest(){
    assertNotNull(diskServ);
  }
  @Test
  public void TestGetAll(){
    assertArrayEquals(listfound.toArray(), diskServ.getAllHdds().toArray());
  }
  @Test
  public void getCpuByIdTest() throws Exception{
    HardDiskDrive test=diskServ.getHardDiskDriveById((long)12345);
    assertEquals(hdd,test);
  }
  @Test
  public void getCpuByBrandTest1() throws Exception{
    assertArrayEquals(diskServ.findByBrand(brand).toArray(), listfound.toArray());
  }
  @Test
  public void getCpuByBrandTest2() throws Exception{
    assertArrayEquals(diskServ.findByBrand("AMD").toArray(), listEmpty.toArray());
  }
  @Test
  public void getCpuBetweenPriceTest1() throws Exception{
    assertArrayEquals(diskServ.findBetweenPrice(20.0,40.0).toArray(), listfound.toArray());
  }
  @Test
  public void getCpuBetweenPriceTest2() throws Exception{
    assertArrayEquals(diskServ.findBetweenPrice(40.0,50.0).toArray(), listEmpty.toArray());
  }

}
