package com.circuitcommerce.project2.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.circuitcommerce.project2.dto.ChargeRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {
  @Value("${STRIPE_SECRET_TEST_KEY}")
  private String stripeSecretTestKey;

  @PostConstruct
  public void init() {
    Stripe.apiKey = stripeSecretTestKey;
  }

  public String charge(ChargeRequest chargeRequest) throws StripeException {
    Map<String, Object> chargeParams = new HashMap<>();
    chargeParams.put("amount", chargeRequest.getAmount());
    chargeParams.put("currency", chargeRequest.getCurrency());
    chargeParams.put("description", chargeRequest.getDescription());
    chargeParams.put("source", chargeRequest.getStripeToken());

    Charge charge = Charge.create(chargeParams);
    return charge.getReceiptUrl();
  }
}
