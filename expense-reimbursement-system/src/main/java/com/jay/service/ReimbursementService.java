package com.jay.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.jay.dao.ReimbursementDao;
import com.jay.dao.UserDao;
import com.jay.dto.ReimbursementDto;
import com.jay.exception.NoSuchReimbursementException;
import com.jay.exception.NoSuchUserException;
import com.jay.model.Reimbursement;
import com.jay.model.ReimbursementStatus;
import com.jay.model.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReimbursementService {
  private final ReimbursementDao reimbursementDao;
  private final UserDao userDao;

  public void save(Reimbursement reimbursement) {
    reimbursement.setSubmittedAt(Instant.now());
    reimbursement.setStatusId(ReimbursementStatus.PENDING);
    String username = reimbursement.getAuthor().getUsername();
    User user = userDao.findByUsername(username).orElseThrow(() -> new NoSuchUserException("User could not be found in save Reimbursement"));
    reimbursement.setAuthor(user);
    reimbursementDao.insert(reimbursement);
  }

  public List<ReimbursementDto> findAllReimbursementsForUser(String username) {
    User user = userDao.findByUsername(username).orElseThrow(() -> new NoSuchUserException("Could not find " + username + " during ReimbursementService"));
    return reimbursementDao.findAllByUsername(user)
        .stream()
        .map(this::mapToDto)
        .collect(Collectors.toList());
  }

  private ReimbursementDto mapToDto(Reimbursement reimbursement) {
    Optional<User> resolver = Optional.ofNullable(reimbursement.getResolver());
    String name = resolver.isPresent() ? resolver.get().getFullName() : "";
    return ReimbursementDto.builder()
        .reimbursementId(reimbursement.getReimbursementId())
        .amount(reimbursement.getAmount())
        .submittedAt(reimbursement.getSubmittedAt())
        .resolvedAt(reimbursement.getResolvedAt())
        .description(reimbursement.getDescription())
        .author(reimbursement.getAuthor().getFullName())
        .resolver(name)
        .receipt(reimbursement.getReceipt())
        .status(reimbursement.getStatusId())
        .type(reimbursement.getTypeId())
        .build();
  }

  public List<ReimbursementDto> filterAllByUser(String username) {
    User user = userDao.findByUsername(username).orElseThrow(
        () -> new NoSuchUserException(
          "User not found when filtering all by user in reimbursement service"));
    return reimbursementDao.findAll()
        .stream()
        .filter(reimb -> reimb.getAuthor().getUserId() != user.getUserId())
        .map(this::mapToDto)
        .collect(Collectors.toList());
  }

  public String update(Reimbursement reimbursement) {
    ReimbursementStatus status = reimbursement.getStatusId();
    User user = userDao.findByUsername(reimbursement.getResolver().getUsername())
        .orElseThrow(
          () -> new NoSuchUserException(
            "No user found during update in reimbursement service"));
    reimbursement = reimbursementDao.find(reimbursement.getReimbursementId())
        .orElseThrow(() -> new NoSuchReimbursementException(
            "Could not find reimbursement"));
    reimbursement.setStatusId(status);
    reimbursement.setResolver(user);
    reimbursement.setResolvedAt(Instant.now());
    reimbursementDao.update(reimbursement);
    return user.getFullName();
  }
}
