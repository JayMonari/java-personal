package com.circuitcommerce.project2.repository;

import java.util.List;

import com.circuitcommerce.project2.model.Review;
import com.circuitcommerce.project2.model.Product;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {

  public List<Review> findByReviewedProduct(Product product);
  
}
