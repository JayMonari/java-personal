package com.circuitcommerce.project2.service;

import java.util.List;

import com.circuitcommerce.project2.dto.VideoCardDto;
import com.circuitcommerce.project2.model.VideoCard;
import com.circuitcommerce.project2.repository.VideoCardRepository;
import com.circuitcommerce.project2.dto.ProductShortDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@_(@Autowired))
@NoArgsConstructor
public class VideoCardService {
  VideoCardRepository vcRepo;

  public VideoCard getVideoCardById(Long id){
    VideoCard vCard = vcRepo.getOne(id);
    return vCard;
}

public List<ProductShortDto> getAllVideoCards(){
    return vcRepo.vcSmall();
 }

  public void insertVideoCard(VideoCardDto vcDto){
    VideoCard videoCard = (VideoCard.builder()
      //Product arguments
      .modelNumber(vcDto.getModelNumber())
      .title(vcDto.getTitle())
      .abbreviatedTitle(vcDto.getAbbreviatedTitle())
      .brand(vcDto.getBrand())
      .price(vcDto.getPrice())
      .cartList(vcDto.getCarts()))
      .orderedBy(vcDto.getOrderedBy())
      //Video Card arguments
      .coreClock(vcDto.getCoreClock())
      .maxResolution(vcDto.getMaxResolution())
      .displayPort(vcDto.getDisplayPort())
      .dvi(vcDto.getDvi())
      .hdmi(vcDto.getHdmi())
      .cardDimensions(vcDto.getCardDimensions())
      .build();
    vcRepo.save(videoCard);
  }

  public List<ProductShortDto> findByBrand(String brand){
    return vcRepo.findByBrand(brand);
  }

  public List<ProductShortDto> findByPrice(double price){
    return vcRepo.findByPrice(price);
  }
  
  public List<ProductShortDto> findBetweenPrice(double priceLow, double priceHigh){
    return vcRepo.findByPriceBetween(priceLow,priceHigh);
  }
  
  public List<ProductShortDto> findByPriceLess(double price){
    return vcRepo.findByPriceLessThan(price);
  }

  public List<ProductShortDto> findByPriceLessEqual(double price){
    return vcRepo.findByPriceLessThanEqual(price);
  }

  public List<ProductShortDto> findByPriceGreater(double price){
    return vcRepo.findByPriceGreaterThan(price);
  }

  public List<ProductShortDto> findByPriceGreaterEqual(double price){
    return vcRepo.findByPriceGreaterThanEqual(price);
  }
  
}
