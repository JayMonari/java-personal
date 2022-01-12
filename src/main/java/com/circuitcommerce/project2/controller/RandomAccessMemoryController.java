package com.circuitcommerce.project2.controller;

import java.util.List;

import com.circuitcommerce.project2.dto.RandomAccessMemoryDto;
import com.circuitcommerce.project2.model.RandomAccessMemory;
import com.circuitcommerce.project2.service.RandomAccessMemoryService;
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
@RequestMapping(value="/ram")
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class RandomAccessMemoryController {

  public RandomAccessMemoryService ramServ;

  @PostMapping("/add")
  public ResponseEntity<String> insertRAM(@RequestBody RandomAccessMemoryDto ramDto){
    ramServ.insertRAM(ramDto);
    return new ResponseEntity<>("RAM Added to Inventory", HttpStatus.CREATED);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<RandomAccessMemory> getRamById(@PathVariable("id") Long id){
    return new ResponseEntity<>(ramServ.getRandomAccessMemoryById(id), HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<ProductShortDto>> getAllRam(){
    return new ResponseEntity<>(ramServ.getAllRandomAccessMemories(), HttpStatus.OK);
  }
  
  @GetMapping("/brand/{brand}")
  public ResponseEntity<List<ProductShortDto>> getRamBybrand(@PathVariable("brand") String brand){
    return new ResponseEntity<>(ramServ.findByBrand(brand), HttpStatus.OK);
  }

  @GetMapping("/price/{price}")
  public ResponseEntity<List<ProductShortDto>> getRamByPrice(@PathVariable("price") double price){
    return new ResponseEntity<>(ramServ.findByPrice(price), HttpStatus.OK);
  }

  @GetMapping("/pricelow/{low}/pricehigh/{high}")
  public ResponseEntity<List<ProductShortDto>> getRamByPriceBetween(@PathVariable("low") double low, @PathVariable("high") double high){
    return new ResponseEntity<>(ramServ.findBetweenPrice(low,high), HttpStatus.OK);
  }

  @GetMapping("/priceless/{price}")
  public ResponseEntity<List<ProductShortDto>> getRamByPriceLess(@PathVariable("price") double price){
    return new ResponseEntity<>(ramServ.findByPriceLess(price), HttpStatus.OK);
  }

  @GetMapping("/pricelessequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getRamByLessOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(ramServ.findByPriceLessEqual(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreater/{price}")
  public ResponseEntity<List<ProductShortDto>> getRamByPriceGreater(@PathVariable("price") double price){
    return new ResponseEntity<>(ramServ.findByPriceGreater(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreaterequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getRamByGreaterOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(ramServ.findByPriceGreaterEqual(price), HttpStatus.OK);
  }
}
