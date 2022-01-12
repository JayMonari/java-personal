package com.circuitcommerce.project2.service;

import java.util.List;

import com.circuitcommerce.project2.dto.CentralProcessingUnitDto;
import com.circuitcommerce.project2.model.CentralProcessingUnit;
import com.circuitcommerce.project2.repository.CentralProcessingUnitRepository;
import com.circuitcommerce.project2.dto.ProductShortDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@_(@Autowired))
@NoArgsConstructor
public class CentralProcessingUnitService {
  private CentralProcessingUnitRepository cpuRepo;
  
  public CentralProcessingUnit getCpuById(Long id){
    CentralProcessingUnit cpu = cpuRepo.getOne(id);
    return cpu;
  }

  public List<ProductShortDto> getAllCpus(){
    return cpuRepo.cpuSmall();
  }

   public void insertCpu(CentralProcessingUnitDto cpuDto){
    CentralProcessingUnit cpu = CentralProcessingUnit.builder()
      //Product arguments
      .modelNumber(cpuDto.getModelNumber())
      .title(cpuDto.getTitle())
      .abbreviatedTitle(cpuDto.getAbbreviatedTitle())
      .brand(cpuDto.getBrand())
      .price(cpuDto.getPrice())
      //CPU arguments
      .series(cpuDto.getSeries())
      .l3Cache(cpuDto.getL3Cache())
      .l2Cache(cpuDto.getL2Cache())
      .coolingDevice(cpuDto.getCoolingDevice())
      .manufacturingTech(cpuDto.getManufacturingTech())
      .build();
    cpuRepo.save(cpu);
  }

  public List<ProductShortDto> findByBrand(String brand){
    return cpuRepo.findByBrand(brand);
  }

  public List<ProductShortDto> findByPrice(double price){
    return cpuRepo.findByPrice(price);
  }
  
  public List<ProductShortDto> findBetweenPrice(double priceLow, double priceHigh){
    return cpuRepo.findByPriceBetween(priceLow,priceHigh);
  }
  
  public List<ProductShortDto> findByPriceLess(double price){
    return cpuRepo.findByPriceLessThan(price);
  }

  public List<ProductShortDto> findByPriceLessEqual(double price){
    return cpuRepo.findByPriceLessThanEqual(price);
  }

  public List<ProductShortDto> findByPriceGreater(double price){
    return cpuRepo.findByPriceGreaterThan(price);
  }

  public List<ProductShortDto> findByPriceGreaterEqual(double price){
    return cpuRepo.findByPriceGreaterThanEqual(price);
  }
}
