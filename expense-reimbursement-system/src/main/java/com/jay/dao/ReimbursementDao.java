package com.jay.dao;

import java.util.List;
import java.util.Optional;

import com.jay.model.Reimbursement;
import com.jay.model.User;

public interface ReimbursementDao extends GenericDao<Reimbursement, Integer> {
  public List<Reimbursement> findAllByUsername(User user);

  public List<Reimbursement> findAll();

  public Optional<Reimbursement> find(int reimbursementId);

  public void update(Reimbursement reimbursement);
}
