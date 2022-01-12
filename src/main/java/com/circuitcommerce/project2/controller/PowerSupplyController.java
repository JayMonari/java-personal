package com.circuitcommerce.project2.controller;

import java.util.List;

import com.circuitcommerce.project2.dto.PowerSupplyDto;
import com.circuitcommerce.project2.model.PowerSupply;
import com.circuitcommerce.project2.service.PowerSupplyService;
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
@RequestMapping(value="/powersupply")
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class PowerSupplyController {
  public PowerSupplyService psServ;

  @PostMapping("/add")
  public ResponseEntity<String> insertPowerSupply(@RequestBody PowerSupplyDto psDto){
    psServ.insertPowerSupply(psDto);
    return new ResponseEntity<>("Power Supply Added to Inventory", HttpStatus.CREATED);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<PowerSupply> getPowerSupplyById(@PathVariable("id") Long id){
    return new ResponseEntity<>(psServ.getPowerSupplyById(id), HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<ProductShortDto>> getAllPowerSupplies(){
    return new ResponseEntity<>(psServ.getAllPowerSupplies(), HttpStatus.OK);
  }

  @GetMapping("/brand/{brand}")
  public ResponseEntity<List<ProductShortDto>> getPsBybrand(@PathVariable("brand") String brand){
    return new ResponseEntity<>(psServ.findByBrand(brand), HttpStatus.OK);
  }

  @GetMapping("/price/{price}")
  public ResponseEntity<List<ProductShortDto>> getPsByPrice(@PathVariable("price") double price){
    return new ResponseEntity<>(psServ.findByPrice(price), HttpStatus.OK);
  }

  @GetMapping("/pricelow/{low}/pricehigh/{high}")
  public ResponseEntity<List<ProductShortDto>> getPsByPriceBetween(@PathVariable("low") double low, @PathVariable("high") double high){
    return new ResponseEntity<>(psServ.findBetweenPrice(low,high), HttpStatus.OK);
  }

  @GetMapping("/priceless/{price}")
  public ResponseEntity<List<ProductShortDto>> getPsByPriceLess(@PathVariable("price") double price){
    return new ResponseEntity<>(psServ.findByPriceLess(price), HttpStatus.OK);
  }

  @GetMapping("/pricelessequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getPsByLessOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(psServ.findByPriceLessEqual(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreater/{price}")
  public ResponseEntity<List<ProductShortDto>> getPsByPriceGreater(@PathVariable("price") double price){
    return new ResponseEntity<>(psServ.findByPriceGreater(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreaterequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getPsByGreaterOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(psServ.findByPriceGreaterEqual(price), HttpStatus.OK);
  }

  
}
