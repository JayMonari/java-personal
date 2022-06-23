package com.circuitcommerce.project2.service;

import com.circuitcommerce.project2.repository.ProductRepository;
import com.circuitcommerce.project2.dto.ProductShortDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.circuitcommerce.project2.dto.ProductDto;
import com.circuitcommerce.project2.model.*;

@Service
@AllArgsConstructor(onConstructor = @_(@Autowired))
@NoArgsConstructor
public class ProductService {
  private ProductRepository prodRepo;

  public void insertProduct(ProductDto pDto) {
    Product product = Product.builder()
      .modelNumber(pDto.getModelNumber())
      .title(pDto.getTitle())
      .abbreviatedTitle(pDto.getAbbreviatedTitle())
      .brand(pDto.getBrand())
      .price(pDto.getPrice())
      .cartList(pDto.getCarts())
      .build();
    prodRepo.save(product);
  }

  public void removeProduct(Long productId) {
    prodRepo.deleteById(productId);
  }

  public Product getProductByProductId(Long id) {
    return prodRepo.findByProductId(id);
  }

  public List<ProductShortDto> getAllProducts() {
    return prodRepo.productsSmall();
  }

  public List<ProductShortDto> findByBrand(String brand) {
    return prodRepo.findByBrand(brand);
  }

  public List<ProductShortDto> findByPrice(double price) {
    return prodRepo.findByPrice(price);
  }

  public List<ProductShortDto> findBetweenPrice(double priceLow, double priceHigh) {
    return prodRepo.findByPriceBetween(priceLow, priceHigh);
  }

  public List<ProductShortDto> findByPriceLess(double price) {
    return prodRepo.findByPriceLessThan(price);
  }

  public List<ProductShortDto> findByPriceLessEqual(double price) {
    return prodRepo.findByPriceLessThanEqual(price);
  }

  public List<ProductShortDto> findByPriceGreater(double price) {
    return prodRepo.findByPriceGreaterThan(price);
  }

  public List<ProductShortDto> findByPriceGreaterEqual(double price) {
    return prodRepo.findByPriceGreaterThanEqual(price);
  }
}