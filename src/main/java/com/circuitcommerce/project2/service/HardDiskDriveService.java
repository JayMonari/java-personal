package com.circuitcommerce.project2.service;

import java.util.List;

import com.circuitcommerce.project2.dto.HardDiskDriveDto;
import com.circuitcommerce.project2.model.HardDiskDrive;
import com.circuitcommerce.project2.repository.HardDiskDriveRepository;
import com.circuitcommerce.project2.dto.ProductShortDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@_(@Autowired))
@NoArgsConstructor
public class HardDiskDriveService {
  public HardDiskDriveRepository hddRepo;

  public HardDiskDrive getHardDiskDriveById(Long id){
    HardDiskDrive hdd = hddRepo.getOne(id);
    return hdd;
  }

  public List<ProductShortDto> getAllHdds(){
    return hddRepo.hddSmall();
  }

  public void insertHDD(HardDiskDriveDto hDDDto){
    HardDiskDrive hDD = HardDiskDrive.builder()
      //Product arguments
      .modelNumber(hDDDto.getModelNumber())
      .title(hDDDto.getTitle())
      .abbreviatedTitle(hDDDto.getAbbreviatedTitle())
      .brand(hDDDto.getBrand())
      .price(hDDDto.getPrice())
      //HDD arguments
      .height(hDDDto.getHeight())
      .width(hDDDto.getWidth())
      .length(hDDDto.getLength())
      .weight(hDDDto.getWeight())
      .packageContents(hDDDto.getPackageContents())
      .averageLatency(hDDDto.getAverageLatency())
      .build();
    hddRepo.save(hDD);
  }

  public List<ProductShortDto> findByBrand(String brand){
    return hddRepo.findByBrand(brand);
  }

  public List<ProductShortDto> findByPrice(double price){
    return hddRepo.findByPrice(price);
  }
  
  public List<ProductShortDto> findBetweenPrice(double priceLow, double priceHigh){
    return hddRepo.findByPriceBetween(priceLow,priceHigh);
  }
  
  public List<ProductShortDto> findByPriceLess(double price){
    return hddRepo.findByPriceLessThan(price);
  }

  public List<ProductShortDto> findByPriceLessEqual(double price){
    return hddRepo.findByPriceLessThanEqual(price);
  }

  public List<ProductShortDto> findByPriceGreater(double price){
    return hddRepo.findByPriceGreaterThan(price);
  }

  public List<ProductShortDto> findByPriceGreaterEqual(double price){
    return hddRepo.findByPriceGreaterThanEqual(price);
  }
}
