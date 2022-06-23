package com.circuitcommerce.project2.service;


import java.util.List;
import java.time.Instant;

import com.circuitcommerce.project2.dto.ReviewDto;
import com.circuitcommerce.project2.model.Review;
import com.circuitcommerce.project2.repository.ReviewRepo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@_(@Autowired))
@NoArgsConstructor
public class ReviewService {
  private ReviewRepo rRepo;
  private UserService uServ;
  private ProductService pServ;

  public void insertReview( ReviewDto rDto){
    Review rev = Review.builder()
      .starRating(rDto.getStarRating())
      .comments(rDto.getComments())
      .createdAt(Instant.now())
      .reviewingUser(uServ.getUser(rDto.getUsername()).orElseThrow(()-> new UsernameNotFoundException("When making review the user " +rDto.getUsername() +" was not found")))
      .reviewedProduct(pServ.getProductByProductId(rDto.getProductId()))
      .build();
    rRepo.save(rev);
  }

  public List<Review> getAllReviews(){
    return rRepo.findAll();
  }

  public List<Review> getReviewsByProduct(Long pId){
    return rRepo.findByReviewedProduct(pServ.getProductByProductId(pId));
  }
  
}
