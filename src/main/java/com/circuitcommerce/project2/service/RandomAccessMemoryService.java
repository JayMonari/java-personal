package com.circuitcommerce.project2.service;

import com.circuitcommerce.project2.repository.RandomAccessMemoryRepository;
import com.circuitcommerce.project2.dto.RandomAccessMemoryDto;
import com.circuitcommerce.project2.dto.ProductShortDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.circuitcommerce.project2.model.RandomAccessMemory;

@Service
@AllArgsConstructor(onConstructor=@_(@Autowired))
@NoArgsConstructor
public class RandomAccessMemoryService {
  public RandomAccessMemoryRepository ramRepo;

  public RandomAccessMemory getRandomAccessMemoryById(Long id){
    RandomAccessMemory ram = ramRepo.getOne(id);
    return ram;
}

public List<ProductShortDto> getAllRandomAccessMemories(){
    return ramRepo.ramSmall();
 }

  public void insertRAM(RandomAccessMemoryDto ramDto){
    RandomAccessMemory ram = RandomAccessMemory.builder()
      //Product arguments
      .modelNumber(ramDto.getModelNumber())
      .title(ramDto.getTitle())
      .abbreviatedTitle(ramDto.getAbbreviatedTitle())
      .brand(ramDto.getBrand())
      .price(ramDto.getPrice())
      //RAM arguments
      .caseLatency(ramDto.getCasLatency())
      .voltage(ramDto.getVoltage())
      .multiChannelKit(ramDto.getMultiChannelKit())
      .timing(ramDto.getTiming())
      .build();
    ramRepo.save(ram);
  }

  public List<ProductShortDto> findByBrand(String brand){
    return ramRepo.findByBrand(brand);
  }

  public List<ProductShortDto> findByPrice(double price){
    return ramRepo.findByPrice(price);
  }
  
  public List<ProductShortDto> findBetweenPrice(double priceLow, double priceHigh){
    return ramRepo.findByPriceBetween(priceLow,priceHigh);
  }
  
  public List<ProductShortDto> findByPriceLess(double price){
    return ramRepo.findByPriceLessThan(price);
  }

  public List<ProductShortDto> findByPriceLessEqual(double price){
    return ramRepo.findByPriceLessThanEqual(price);
  }

  public List<ProductShortDto> findByPriceGreater(double price){
    return ramRepo.findByPriceGreaterThan(price);
  }

  public List<ProductShortDto> findByPriceGreaterEqual(double price){
    return ramRepo.findByPriceGreaterThanEqual(price);
  }
  
}
