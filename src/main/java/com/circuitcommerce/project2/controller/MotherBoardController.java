package com.circuitcommerce.project2.controller;

import java.util.List;

import com.circuitcommerce.project2.dto.MotherBoardDto;
import com.circuitcommerce.project2.model.MotherBoard;
import com.circuitcommerce.project2.service.MotherBoardService;
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
@RequestMapping(value="/motherboard")
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class MotherBoardController {
  private MotherBoardService mbServ;

  @PostMapping("/add")
  public ResponseEntity<String> insertMotherBoard(@RequestBody MotherBoardDto mbDto){
    mbServ.insertMotherBoard(mbDto);
    return new ResponseEntity<>("Product Added to Inventory", HttpStatus.CREATED);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<MotherBoard> getMotherboardById(@PathVariable("id") Long id){
    return new ResponseEntity<>(mbServ.getMotherboardById(id), HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<ProductShortDto>> getAllMotherboards(){
    return new ResponseEntity<>(mbServ.getAllMotherBoards(), HttpStatus.OK);
  }

  @GetMapping("/brand/{brand}")
  public ResponseEntity<List<ProductShortDto>> getMotherBoardsBybrand(@PathVariable("brand") String brand){
    return new ResponseEntity<>(mbServ.findByBrand(brand), HttpStatus.OK);
  }

  @GetMapping("/price/{price}")
  public ResponseEntity<List<ProductShortDto>> getMotherBoardsByPrice(@PathVariable("price") double price){
    return new ResponseEntity<>(mbServ.findByPrice(price), HttpStatus.OK);
  }

  @GetMapping("/pricelow/{low}/pricehigh/{high}")
  public ResponseEntity<List<ProductShortDto>> getMotherBoardsByPriceBetween(@PathVariable("low") double low, @PathVariable("high") double high){
    return new ResponseEntity<>(mbServ.findBetweenPrice(low,high), HttpStatus.OK);
  }

  @GetMapping("/priceless/{price}")
  public ResponseEntity<List<ProductShortDto>> getMotherBoardsByPriceLess(@PathVariable("price") double price){
    return new ResponseEntity<>(mbServ.findByPriceLess(price), HttpStatus.OK);
  }

  @GetMapping("/pricelessequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getMotherBoardsByLessOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(mbServ.findByPriceLessEqual(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreater/{price}")
  public ResponseEntity<List<ProductShortDto>> getMotherBoardsByPriceGreater(@PathVariable("price") double price){
    return new ResponseEntity<>(mbServ.findByPriceGreater(price), HttpStatus.OK);
  }

  @GetMapping("/pricegreaterequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getMotherBoardsByGreaterOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(mbServ.findByPriceGreaterEqual(price), HttpStatus.OK);
  }
}
