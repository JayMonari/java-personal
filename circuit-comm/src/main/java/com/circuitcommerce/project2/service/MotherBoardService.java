package com.circuitcommerce.project2.service;


import java.util.List;

import com.circuitcommerce.project2.dto.MotherBoardDto;
import com.circuitcommerce.project2.model.MotherBoard;
import com.circuitcommerce.project2.repository.MotherBoardRepository;
import com.circuitcommerce.project2.dto.ProductShortDto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@_(@Autowired))
@NoArgsConstructor
public class MotherBoardService {

  private MotherBoardRepository mbRepo;

  public MotherBoard getMotherboardById(Long id){
    MotherBoard mBoard = mbRepo.getOne(id);
    return mBoard;
  }

  public List<ProductShortDto> getAllMotherBoards(){
    return mbRepo.mbSmall();
  }

   public void insertMotherBoard(MotherBoardDto mbDto){
     MotherBoard mBoard = MotherBoard.builder()
      //Product arguments
      .modelNumber(mbDto.getModelNumber())
      .title(mbDto.getTitle())
      .abbreviatedTitle(mbDto.getAbbreviatedTitle())
      .brand(mbDto.getBrand())
      .price(mbDto.getPrice())
      //Motherboard arguments
      .memoryStandard(mbDto.getMemoryStandard())
      .numOfMemorySlots(mbDto.getNumOfMemorySlots())
      .audioChipset(mbDto.getAudioChipset())
      .onboardVideoChipset(mbDto.getOnboardVideoChipset())
      .pciExpress(mbDto.getPciExpress())
      .build();
     mbRepo.save(mBoard);
   }

   public List<ProductShortDto> findByBrand(String brand){
    return mbRepo.findByBrand(brand);
  }

  public List<ProductShortDto> findByPrice(double price){
    return mbRepo.findByPrice(price);
  }
  
  public List<ProductShortDto> findBetweenPrice(double priceLow, double priceHigh){
    return mbRepo.findByPriceBetween(priceLow,priceHigh);
  }
  
  public List<ProductShortDto> findByPriceLess(double price){
    return mbRepo.findByPriceLessThan(price);
  }

  public List<ProductShortDto> findByPriceLessEqual(double price){
    return mbRepo.findByPriceLessThanEqual(price);
  }

  public List<ProductShortDto> findByPriceGreater(double price){
    return mbRepo.findByPriceGreaterThan(price);
  }

  public List<ProductShortDto> findByPriceGreaterEqual(double price){
    return mbRepo.findByPriceGreaterThanEqual(price);
  }
}
