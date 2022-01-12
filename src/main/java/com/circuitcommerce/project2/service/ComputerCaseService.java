package com.circuitcommerce.project2.service;

import java.util.List;

import com.circuitcommerce.project2.dto.ComputerCaseDto;
import com.circuitcommerce.project2.model.ComputerCase;
import com.circuitcommerce.project2.repository.ComputerCaseRepository;
import com.circuitcommerce.project2.dto.ProductShortDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@_(@Autowired))
@NoArgsConstructor
public class ComputerCaseService {

  ComputerCaseRepository ccRepo;

  public ComputerCase getComputerCaseById(Long id){
    ComputerCase compCase = ccRepo.getOne(id);
    return compCase;
  }

  public List<ProductShortDto> getallComputerCases(){
    return ccRepo.caseSmall();
  }

  public void insertComputerCase(ComputerCaseDto ccDto){
    ComputerCase compCase = ComputerCase.builder()
      //Product Arguments
      .modelNumber(ccDto.getModelNumber())
      .title(ccDto.getTitle())
      .abbreviatedTitle(ccDto.getAbbreviatedTitle())
      .brand(ccDto.getBrand())
      .price(ccDto.getPrice())
      .cartList(ccDto.getCarts())
      //Computer Case Arguments
      .motherboardCompatibility(ccDto.getMotherboardCompatibility())
      .frontPorts(ccDto.getFrontPorts())
      .powerSupplyMounted(ccDto.getPowerSupplyMounted())
      .sidePannelWindow(ccDto.getSidePannelWindow())
      .fans(ccDto.getFans())
      .internalDriveBays(ccDto.getInternalDriveBays())
      .build();
    ccRepo.save(compCase);
  }

  public List<ProductShortDto> findByBrand(String brand){
    return ccRepo.findByBrand(brand);
  }

  public List<ProductShortDto> findByPrice(double price){
    return ccRepo.findByPrice(price);
  }
  
  public List<ProductShortDto> findBetweenPrice(double priceLow, double priceHigh){
    return ccRepo.findByPriceBetween(priceLow,priceHigh);
  }
  
  public List<ProductShortDto> findByPriceLess(double price){
    return ccRepo.findByPriceLessThan(price);
  }

  public List<ProductShortDto> findByPriceLessEqual(double price){
    return ccRepo.findByPriceLessThanEqual(price);
  }

  public List<ProductShortDto> findByPriceGreater(double price){
    return ccRepo.findByPriceGreaterThan(price);
  }

  public List<ProductShortDto> findByPriceGreaterEqual(double price){
    return ccRepo.findByPriceGreaterThanEqual(price);
  }
}
