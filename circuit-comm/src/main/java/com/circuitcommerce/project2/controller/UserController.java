package com.circuitcommerce.project2.controller;


import java.util.Optional;
import java.util.List;

import com.circuitcommerce.project2.dto.UserDto;
import com.circuitcommerce.project2.service.UserService;
import com.circuitcommerce.project2.model.User;
import com.circuitcommerce.project2.model.UserOrder;

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
@RequestMapping(value="/user")
@AllArgsConstructor(onConstructor=@__(@Autowired))
@NoArgsConstructor
public class UserController {
  public UserService uServ;

  @PostMapping("/update")
  public ResponseEntity<String> updateUser(@RequestBody UserDto uDto ){ 
    uServ.updateUser(uDto);
    return new ResponseEntity<>("User Profile Updated", HttpStatus.NO_CONTENT);
  }

  @GetMapping("/username/{username}")
  public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable("username") String username){
    return new ResponseEntity<>(uServ.getUser(username), HttpStatus.OK);
  }
}