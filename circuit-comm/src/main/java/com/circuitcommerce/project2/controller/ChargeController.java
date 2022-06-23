package com.circuitcommerce.project2.controller;

import com.circuitcommerce.project2.dto.ChargeRequest;
import com.circuitcommerce.project2.service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/stripe")
public class ChargeController {
  private final StripeService stripeService;

  @PostMapping("/charge")
  public void charge(@RequestBody ChargeRequest chargeRequest)
        throws StripeException {
    stripeService.charge(chargeRequest);
    // return new ResponseEntity<>(charge.toJson(), HttpStatus.OK); //TODO
  }

  @ExceptionHandler(StripeException.class)
  public ResponseEntity<String> handleError(Model model, StripeException ex) {
    model.addAttribute("error", ex.getMessage());
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }
}
