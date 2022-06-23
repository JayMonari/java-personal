package com.circuitcommerce.project2.serviceTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.circuitcommerce.project2.dto.ProductShortDto;
import com.circuitcommerce.project2.model.VideoCard;
import com.circuitcommerce.project2.repository.VideoCardRepository;
import com.circuitcommerce.project2.service.VideoCardService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GPUServiceTests {
  @Mock
  private VideoCardRepository gpuRepo;
  @InjectMocks
  private VideoCardService gpuServ;
  private VideoCard gpu;
  private ArrayList<ProductShortDto> listfound;
  private ArrayList<ProductShortDto> listEmpty;

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

    pos=new ProductShortDto(productId, title, brand, price, null);

    listfound=new ArrayList<>();
    listEmpty=new ArrayList<>();

    listfound.add(pos);

    when(gpuRepo.getOne((long)12345)).thenReturn(gpu);
    when(gpuRepo.vcSmall()).thenReturn(listfound);
    when(gpuRepo.findByBrand(brand)).thenReturn(listfound);
    when(gpuRepo.findByBrand("Gigabyte")).thenReturn(listEmpty);
    when(gpuRepo.findByPriceBetween(300.00,400.00)).thenReturn(listfound);
    when(gpuRepo.findByPriceBetween(190.00,200.00)).thenReturn(listEmpty);
  }
  @AfterEach
  public void after(){

  }

  @Test
  public void NotNullTest(){
    assertThat(gpuServ).isNotNull();
  }
  @Test
  public void TestGetAll(){
    assertArrayEquals(listfound.toArray(), gpuServ.getAllVideoCards().toArray());
  }
  @Test
  public void getVideoCardByIdTest() throws Exception{
    VideoCard test=gpuServ.getVideoCardById((long)12345);
    assertEquals(gpu,test);
  }
  @Test
  public void getallComputerCasesTest() throws Exception{
    assertArrayEquals(gpuServ.getAllVideoCards().toArray(), listfound.toArray());
  }
  @Test
  public void getVideoCardByBrandTest1() throws Exception{
    assertArrayEquals(gpuServ.findByBrand(brand).toArray(), listfound.toArray());
  }
  @Test
  public void getVideoCardByBrandTest2() throws Exception{
    assertArrayEquals(gpuServ.findByBrand("Gigabyte").toArray(), listEmpty.toArray());
  }
  @Test
  public void getVideoCardBetweenPriceTest1() throws Exception{
    assertArrayEquals(gpuServ.findBetweenPrice(300.00,400.00).toArray(), listfound.toArray());
  }
  @Test
  public void getVideoCardBetweenPriceTest2() throws Exception{
    assertArrayEquals(gpuServ.findBetweenPrice(190.00,200.00).toArray(), listEmpty.toArray());
  }

}
