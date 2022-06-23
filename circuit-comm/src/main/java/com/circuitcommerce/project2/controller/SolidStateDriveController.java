package com.circuitcommerce.project2.controller;

import java.util.List;

import com.circuitcommerce.project2.dto.SolidStateDriveDto;
import com.circuitcommerce.project2.model.SolidStateDrive;
import com.circuitcommerce.project2.service.SolidStateDriveService;
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
@RequestMapping(value="/ssd")
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class SolidStateDriveController {
  public SolidStateDriveService ssdServ;

  @PostMapping("/add")
  public ResponseEntity<String> insertSolidStateDrive(@RequestBody SolidStateDriveDto ssdDto){
    ssdServ.insertSSD(ssdDto);
    return new ResponseEntity<>("SSD Added to Inventory", HttpStatus.CREATED);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<SolidStateDrive> getSsdById(@PathVariable("id") Long id){
    return new ResponseEntity<>(ssdServ.getSolidStateDriveById(id), HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<ProductShortDto>> getAllSolidStateDrives(){
    return new ResponseEntity<>(ssdServ.getAllSolidStateDrives(), HttpStatus.OK);
  }

  @GetMapping("/brand/{brand}")
  public ResponseEntity<List<ProductShortDto>> getSsdBybrand(@PathVariable("brand") String brand){
    return new ResponseEntity<>(ssdServ.findByBrand(brand), HttpStatus.OK);
  }

  @GetMapping("/price/{price}")
  public ResponseEntity<List<ProductShortDto>> getSsdByPrice(@PathVariable("price") double price){
    return new ResponseEntity<>(ssdServ.findByPrice(price), HttpStatus.OK);
  }

  @GetMapping("/pricelow/{low}/pricehigh/{high}")
  public ResponseEntity<List<ProductShortDto>> getSsdByPriceBetween(@PathVariable("low") double low, @PathVariable("high") double high){
    return new ResponseEntity<>(ssdServ.findBetweenPrice(low,high), HttpStatus.OK);
  }

  @GetMapping("/priceless/{price}")
  public ResponseEntity<List<ProductShortDto>> getSsdByPriceLess(@PathVariable("price") double price){
    return new ResponseEntity<>(ssdServ.findByPriceLess(price), HttpStatus.OK);
  }

  @GetMapping("/pricelessequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getSsdByLessOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(ssdServ.findByPriceLessEqual(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreater/{price}")
  public ResponseEntity<List<ProductShortDto>> getSsdByPriceGreater(@PathVariable("price") double price){
    return new ResponseEntity<>(ssdServ.findByPriceGreater(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreaterequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getSsdByGreaterOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(ssdServ.findByPriceGreaterEqual(price), HttpStatus.OK);
  }
}
