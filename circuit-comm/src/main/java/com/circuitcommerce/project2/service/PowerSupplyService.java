package com.circuitcommerce.project2.service;

import com.circuitcommerce.project2.repository.PowerSupplyRepository;
import com.circuitcommerce.project2.dto.PowerSupplyDto;
import com.circuitcommerce.project2.dto.ProductShortDto;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.circuitcommerce.project2.model.PowerSupply;

@Service
@AllArgsConstructor(onConstructor=@_(@Autowired))
@NoArgsConstructor
public class PowerSupplyService {
  public PowerSupplyRepository psRepo;

  public PowerSupply getPowerSupplyById(Long id){
    PowerSupply powSup = psRepo.getOne(id);
    return powSup;
  }

  public List<ProductShortDto> getAllPowerSupplies(){
    return psRepo.psSmall();
  }

  public void insertPowerSupply(PowerSupplyDto powSupDto){
    PowerSupply pSup  = PowerSupply.builder()
      //Product arguments
      .modelNumber(powSupDto.getModelNumber())
      .title(powSupDto.getTitle())
      .abbreviatedTitle(powSupDto.getAbbreviatedTitle())
      .brand(powSupDto.getBrand())
      .price(powSupDto.getPrice())
      //PowerSupply arguments
      .fans(powSupDto.getFans())
      .mainConnector(powSupDto.getMainConnector())
      .rails(powSupDto.getRails())
      .pciExpressConnector(powSupDto.getPciExpressConnector())
      .build();
    psRepo.save(pSup);
  }

  public List<ProductShortDto> findByBrand(String brand){
    return psRepo.findByBrand(brand);
  }

  public List<ProductShortDto> findByPrice(double price){
    return psRepo.findByPrice(price);
  }
  
  public List<ProductShortDto> findBetweenPrice(double priceLow, double priceHigh){
    return psRepo.findByPriceBetween(priceLow,priceHigh);
  }
  
  public List<ProductShortDto> findByPriceLess(double price){
    return psRepo.findByPriceLessThan(price);
  }

  public List<ProductShortDto> findByPriceLessEqual(double price){
    return psRepo.findByPriceLessThanEqual(price);
  }

  public List<ProductShortDto> findByPriceGreater(double price){
    return psRepo.findByPriceGreaterThan(price);
  }

  public List<ProductShortDto> findByPriceGreaterEqual(double price){
    return psRepo.findByPriceGreaterThanEqual(price);
  }
  
}
