package com.circuitcommerce.project2.service;

import java.util.Optional;

import com.circuitcommerce.project2.dto.UserDto;
import com.circuitcommerce.project2.model.User;
import com.circuitcommerce.project2.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor=@_(@Autowired))
public class UserService {

  private UserRepository uRepo;

  public Optional<User> getUser(String username){
    return uRepo.findByUsername(username);
  }
  public void updateUser(UserDto uDto){
    User origUser = uRepo.findByUsername(uDto.getUsername()).orElseThrow(()-> new UsernameNotFoundException("When updating user, username was not found"));
    origUser.setEmail(uDto.getEmail());
    origUser.setFirstName(uDto.getFirstName());
    origUser.setLastName(uDto.getLastName());
    origUser.setAddress(uDto.getAddress());
    uRepo.save(origUser);
  }
}
