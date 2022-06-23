package com.circuitcommerce.project2.controller;

import java.util.List;

import com.circuitcommerce.project2.dto.ComputerCaseDto;
import com.circuitcommerce.project2.model.ComputerCase;
import com.circuitcommerce.project2.service.ComputerCaseService;
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
@RequestMapping(value="/computercase")
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class ComputerCaseController {
  private ComputerCaseService ccServ;

  @PostMapping("/add")
  public ResponseEntity<String> insertComputerCase(@RequestBody ComputerCaseDto ccDto){
    ccServ.insertComputerCase(ccDto);
    return new ResponseEntity<>("Computer Case Added to Inventory", HttpStatus.CREATED);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<ComputerCase> getComputerCaseById(@PathVariable("id") Long id){
    return new ResponseEntity<>(ccServ.getComputerCaseById(id), HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<ProductShortDto>> getAllComputerCases(){
    return new ResponseEntity<>(ccServ.getallComputerCases(), HttpStatus.OK);
  }

  @GetMapping("/brand/{brand}")
  public ResponseEntity<List<ProductShortDto>> getCcBybrand(@PathVariable("brand") String brand){
    return new ResponseEntity<>(ccServ.findByBrand(brand), HttpStatus.OK);
  }

  @GetMapping("/price/{price}")
  public ResponseEntity<List<ProductShortDto>> getCcByPrice(@PathVariable("price") double price){
    return new ResponseEntity<>(ccServ.findByPrice(price), HttpStatus.OK);
  }

  @GetMapping("/pricelow/{low}/pricehigh/{high}")
  public ResponseEntity<List<ProductShortDto>> getCcByPriceBetween(@PathVariable("low") double low, @PathVariable("high") double high){
    return new ResponseEntity<>(ccServ.findBetweenPrice(low,high), HttpStatus.OK);
  }

  @GetMapping("/priceless/{price}")
  public ResponseEntity<List<ProductShortDto>> getCcByPriceLess(@PathVariable("price") double price){
    return new ResponseEntity<>(ccServ.findByPriceLess(price), HttpStatus.OK);
  }

  @GetMapping("/pricelessequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getCcByLessOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(ccServ.findByPriceLessEqual(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreater/{price}")
  public ResponseEntity<List<ProductShortDto>> getCcByPriceGreater(@PathVariable("price") double price){
    return new ResponseEntity<>(ccServ.findByPriceGreater(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreaterequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getCcByGreaterOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(ccServ.findByPriceGreaterEqual(price), HttpStatus.OK);
  }
}
