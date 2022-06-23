package com.circuitcommerce.project2.controller;

import java.util.List;

import com.circuitcommerce.project2.dto.HardDiskDriveDto;
import com.circuitcommerce.project2.model.HardDiskDrive;
import com.circuitcommerce.project2.service.HardDiskDriveService;
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
@RequestMapping(value="/hdd")
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class HardDiskDriveController {
  private HardDiskDriveService hddServ;

  @PostMapping("/add")
  public ResponseEntity<String> insertHDD(@RequestBody HardDiskDriveDto hddDto){
    hddServ.insertHDD(hddDto);
    return new ResponseEntity<>("HDD Added to Inventory", HttpStatus.CREATED);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<HardDiskDrive> getHardDiskDriveById(@PathVariable("id") Long id){
    return new ResponseEntity<>(hddServ.getHardDiskDriveById(id), HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<ProductShortDto>> getAllMotherboards(){
    return new ResponseEntity<>(hddServ.getAllHdds(), HttpStatus.OK);
  }
  
  
  @GetMapping("/brand/{brand}")
  public ResponseEntity<List<ProductShortDto>> geHhdBybrand(@PathVariable("brand") String brand){
    return new ResponseEntity<>(hddServ.findByBrand(brand), HttpStatus.OK);
  }

  @GetMapping("/price/{price}")
  public ResponseEntity<List<ProductShortDto>> getHhdByPrice(@PathVariable("price") double price){
    return new ResponseEntity<>(hddServ.findByPrice(price), HttpStatus.OK);
  }

  @GetMapping("/pricelow/{low}/pricehigh/{high}")
  public ResponseEntity<List<ProductShortDto>> getHhdByPriceBetween(@PathVariable("low") double low, @PathVariable("high") double high){
    return new ResponseEntity<>(hddServ.findBetweenPrice(low,high), HttpStatus.OK);
  }

  @GetMapping("/priceless/{price}")
  public ResponseEntity<List<ProductShortDto>> getHhdByPriceLess(@PathVariable("price") double price){
    return new ResponseEntity<>(hddServ.findByPriceLess(price), HttpStatus.OK);
  }

  @GetMapping("/pricelessequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getHhdByLessOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(hddServ.findByPriceLessEqual(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreater/{price}")
  public ResponseEntity<List<ProductShortDto>> getHhdByPriceGreater(@PathVariable("price") double price){
    return new ResponseEntity<>(hddServ.findByPriceGreater(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreaterequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getHhdByGreaterOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(hddServ.findByPriceGreaterEqual(price), HttpStatus.OK);
  }

}
