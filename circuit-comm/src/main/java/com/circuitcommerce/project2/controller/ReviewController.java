package com.circuitcommerce.project2.controller;



import java.util.Optional;
import java.util.List;

import com.circuitcommerce.project2.model.Review;
import com.circuitcommerce.project2.dto.ReviewDto;
import com.circuitcommerce.project2.service.ReviewService;

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
@RequestMapping(value="/review")
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class ReviewController {
  
  public ReviewService rServ;
  @GetMapping("/all/{pId}")
  public ResponseEntity<List<Review>> getAllReviews(@PathVariable("pId") Long pId){
    return new ResponseEntity<>(rServ.getReviewsByProduct(pId), HttpStatus.ACCEPTED);
  }

  @PostMapping("/add")
  public ResponseEntity<String> postMethodName(@RequestBody ReviewDto rDto) {
    rServ.insertReview(rDto);
    return new ResponseEntity<>("Review Added", HttpStatus.CREATED);
  }
  
}
