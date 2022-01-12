package com.circuitcommerce.project2.controller;

import java.util.List;

import com.circuitcommerce.project2.dto.CentralProcessingUnitDto;
import com.circuitcommerce.project2.model.CentralProcessingUnit;
import com.circuitcommerce.project2.service.CentralProcessingUnitService;
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
@RequestMapping(value="/cpu")
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class CentralProcessingUnitController {

  private CentralProcessingUnitService cpuServ;

  @PostMapping("/add")
  public ResponseEntity<String> insertCentralProcessingUnit(@RequestBody CentralProcessingUnitDto cpuDto){
    cpuServ.insertCpu(cpuDto);
    return new ResponseEntity<>("CPU Added to Inventory", HttpStatus.CREATED);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<CentralProcessingUnit> getCpuById(@PathVariable("id") Long id){
    return new ResponseEntity<>(cpuServ.getCpuById(id), HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<ProductShortDto>> getAllCpus(){
    return new ResponseEntity<>(cpuServ.getAllCpus(), HttpStatus.OK);
  }

  @GetMapping("/brand/{brand}")
  public ResponseEntity<List<ProductShortDto>> getCpuBybrand(@PathVariable("brand") String brand){
    return new ResponseEntity<>(cpuServ.findByBrand(brand), HttpStatus.OK);
  }

  @GetMapping("/price/{price}")
  public ResponseEntity<List<ProductShortDto>> getCpuByPrice(@PathVariable("price") double price){
    return new ResponseEntity<>(cpuServ.findByPrice(price), HttpStatus.OK);
  }

  @GetMapping("/pricelow/{low}/pricehigh/{high}")
  public ResponseEntity<List<ProductShortDto>> getCpuByPriceBetween(@PathVariable("low") double low, @PathVariable("high") double high){
    return new ResponseEntity<>(cpuServ.findBetweenPrice(low,high), HttpStatus.OK);
  }

  @GetMapping("/priceless/{price}")
  public ResponseEntity<List<ProductShortDto>> getCpuByPriceLess(@PathVariable("price") double price){
    return new ResponseEntity<>(cpuServ.findByPriceLess(price), HttpStatus.OK);
  }

  @GetMapping("/pricelessequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getCpuByLessOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(cpuServ.findByPriceLessEqual(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreater/{price}")
  public ResponseEntity<List<ProductShortDto>> getCpuByPriceGreater(@PathVariable("price") double price){
    return new ResponseEntity<>(cpuServ.findByPriceGreater(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreaterequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getCpuByGreaterOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(cpuServ.findByPriceGreaterEqual(price), HttpStatus.OK);
  }
}