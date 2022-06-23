package com.circuitcommerce.project2.controller;

import java.util.List;

import com.circuitcommerce.project2.dto.VideoCardDto;
import com.circuitcommerce.project2.model.VideoCard;
import com.circuitcommerce.project2.service.VideoCardService;
import com.circuitcommerce.project2.dto.ProductShortDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@RestController
@RequestMapping(value="/videocard")
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class VideoCardController {
  public VideoCardService vcServ;

  @PostMapping("/add")
  public ResponseEntity<String> insertVideoCard(@RequestBody VideoCardDto vcDto){
    vcServ.insertVideoCard(vcDto);
    return new ResponseEntity<>("Video Card Added to Inventory", HttpStatus.CREATED);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<VideoCard> getVideoCardById(@PathVariable("id") Long id){
    return new ResponseEntity<>(vcServ.getVideoCardById(id), HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<ProductShortDto>> getAllVideoCards(){
    return new ResponseEntity<>(vcServ.getAllVideoCards(), HttpStatus.OK);
  }

  @GetMapping("/brand/{brand}")
  public ResponseEntity<List<ProductShortDto>> getVcBybrand(@PathVariable("brand") String brand){
    return new ResponseEntity<>(vcServ.findByBrand(brand), HttpStatus.OK);
  }

  @GetMapping("/price/{price}")
  public ResponseEntity<List<ProductShortDto>> getVcByPrice(@PathVariable("price") double price){
    return new ResponseEntity<>(vcServ.findByPrice(price), HttpStatus.OK);
  }

  @GetMapping("/pricelow/{low}/pricehigh/{high}")
  public ResponseEntity<List<ProductShortDto>> getVcByPriceBetween(@PathVariable("low") double low, @PathVariable("high") double high){
    return new ResponseEntity<>(vcServ.findBetweenPrice(low,high), HttpStatus.OK);
  }

  @GetMapping("/priceless/{price}")
  public ResponseEntity<List<ProductShortDto>> getVcByPriceLess(@PathVariable("price") double price){
    return new ResponseEntity<>(vcServ.findByPriceLess(price), HttpStatus.OK);
  }

  @GetMapping("/pricelessequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getVcByLessOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(vcServ.findByPriceLessEqual(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreater/{price}")
  public ResponseEntity<List<ProductShortDto>> getVcByPriceGreater(@PathVariable("price") double price){
    return new ResponseEntity<>(vcServ.findByPriceGreater(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreaterequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getVcByGreaterOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(vcServ.findByPriceGreaterEqual(price), HttpStatus.OK);
  }
}
