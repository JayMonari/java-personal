package com.circuitcommerce.project2.service;

import com.circuitcommerce.project2.repository.SolidStateDriveRepository;
import com.circuitcommerce.project2.dto.SolidStateDriveDto;
import com.circuitcommerce.project2.dto.ProductShortDto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.circuitcommerce.project2.model.SolidStateDrive;

@Service
@AllArgsConstructor(onConstructor=@_(@Autowired))
@NoArgsConstructor

public class SolidStateDriveService {
  public SolidStateDriveRepository ssdRepo;

  public SolidStateDrive getSolidStateDriveById(Long id){
    return ssdRepo.getOne(id);
}

public List<ProductShortDto> getAllSolidStateDrives(){
    return ssdRepo.ssdSmall();
 }

  public void insertSSD(SolidStateDriveDto ssdDto){
    SolidStateDrive ssd = SolidStateDrive.builder()
      //Product arguments
      .modelNumber(ssdDto.getModelNumber())
      .title(ssdDto.getTitle())
      .abbreviatedTitle(ssdDto.getAbbreviatedTitle())
      .brand(ssdDto.getBrand())
      .price(ssdDto.getPrice())
      //SSD arguments
      .maxSeqRead(ssdDto.getMaxSeqRead())
      .maxSeqWrite(ssdDto.getMaxSeqWrite())
      .usedFor(ssdDto.getUsedFor())
      .mttf(ssdDto.getMttf())
      .kb4RandomRead(ssdDto.getKb4RandomRead())
      .kb4RandomWrite(ssdDto.getKb4RandomWrite())
      .controller(ssdDto.getController())
      .build();
    ssdRepo.save(ssd);
  }

  public List<ProductShortDto> findByBrand(String brand){
    return ssdRepo.findByBrand(brand);
  }

  public List<ProductShortDto> findByPrice(double price){
    return ssdRepo.findByPrice(price);
  }
  
  public List<ProductShortDto> findBetweenPrice(double priceLow, double priceHigh){
    return ssdRepo.findByPriceBetween(priceLow,priceHigh);
  }
  
  public List<ProductShortDto> findByPriceLess(double price){
    return ssdRepo.findByPriceLessThan(price);
  }

  public List<ProductShortDto> findByPriceLessEqual(double price){
    return ssdRepo.findByPriceLessThanEqual(price);
  }

  public List<ProductShortDto> findByPriceGreater(double price){
    return ssdRepo.findByPriceGreaterThan(price);
  }

  public List<ProductShortDto> findByPriceGreaterEqual(double price){
    return ssdRepo.findByPriceGreaterThanEqual(price);
  }
}
