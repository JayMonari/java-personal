package com.circuitcommerce.project2.controller;

import com.circuitcommerce.project2.dto.ProductDto;
import com.circuitcommerce.project2.model.Product;
import com.circuitcommerce.project2.service.ProductService;
import com.circuitcommerce.project2.dto.ProductIdDto;
import com.circuitcommerce.project2.dto.ProductShortDto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@RestController
@RequestMapping(value="/product")
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class ProductController {
  private ProductService prodServ;

  @PostMapping("/add")
  public ResponseEntity<String> insertProduct(@RequestBody ProductDto pDto) {
    prodServ.insertProduct(pDto);
    return new ResponseEntity<>("Product Added to Inventory", HttpStatus.CREATED);
  }

  @DeleteMapping("/remove")
  public ResponseEntity<String> deleteProduct(@RequestBody ProductIdDto idDto){
    prodServ.removeProduct(idDto.getProductId());
    return new ResponseEntity<>("Product Removed from Inventory", HttpStatus.OK);
  }

// specific all product endpoints
  @GetMapping("/all")
  public ResponseEntity<List<ProductShortDto>> getProductsAll(){
    return new ResponseEntity<>(prodServ.getAllProducts(), HttpStatus.OK);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
    return new ResponseEntity<>(prodServ.getProductByProductId(id), HttpStatus.OK);
  }

  @GetMapping("/all/brand/{brand}")
  public ResponseEntity<List<ProductShortDto>> getProductBybrand(@PathVariable("brand") String brand){
    return new ResponseEntity<>(prodServ.findByBrand(brand), HttpStatus.OK);
  }

  @GetMapping("/all/price/{price}")
  public ResponseEntity<List<ProductShortDto>> getProductByPrice(@PathVariable("price") double price){
    return new ResponseEntity<>(prodServ.findByPrice(price), HttpStatus.OK);
  }

  @GetMapping("/all/pricelow/{low}/pricehigh/{high}")
  public ResponseEntity<List<ProductShortDto>> getProductByPriceBetween(@PathVariable("low") double low, @PathVariable("high") double high){
    return new ResponseEntity<>(prodServ.findBetweenPrice(low,high), HttpStatus.OK);
  }

  @GetMapping("/all/priceless/{price}")
  public ResponseEntity<List<ProductShortDto>> getProductByPriceLess(@PathVariable("price") double price){
    return new ResponseEntity<>(prodServ.findByPriceLess(price), HttpStatus.OK);
  }

  @GetMapping("/all/pricelessequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getProductByLessOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(prodServ.findByPriceLessEqual(price), HttpStatus.OK);
  }

  @GetMapping("/all/pricegreater/{price}")
  public ResponseEntity<List<ProductShortDto>> getProductByPriceGreater(@PathVariable("price") double price){
    return new ResponseEntity<>(prodServ.findByPriceGreater(price), HttpStatus.OK);
  }

  @GetMapping("/all/pricegreaterequ/{price}")
  public ResponseEntity<List<ProductShortDto>> getProductByGreaterOrEqual(@PathVariable("price") double price){
    return new ResponseEntity<>(prodServ.findByPriceGreaterEqual(price), HttpStatus.OK);
  }
}