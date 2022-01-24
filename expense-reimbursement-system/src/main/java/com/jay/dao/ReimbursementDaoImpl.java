package com.jay.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.jay.model.Reimbursement;
import com.jay.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReimbursementDaoImpl implements ReimbursementDao {
  private final SessionFactory sessionFactory;

  @Override
  public void insert(Reimbursement reimbursement) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.beginTransaction();
      session.save(reimbursement);
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Reimbursement> findAllByUsername(User user) {
    return user.getReimbursements();
  }

  @Override
  public List<Reimbursement> findAll() {
    try (Session session = sessionFactory.openSession()) {
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
      
      Root<Reimbursement> rootEntry = cq.from(Reimbursement.class);
      CriteriaQuery<Reimbursement> all = cq.select(rootEntry);
      cq.orderBy(cb.desc(rootEntry.get("submittedAt")));

      TypedQuery<Reimbursement> allQuery = session.createQuery(all);
      return allQuery.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Optional<Reimbursement> find(int reimbursementId) {
    Reimbursement reimbursement = null;
    try (Session session = sessionFactory.openSession()) {
      reimbursement = session.get(Reimbursement.class, reimbursementId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Optional.ofNullable(reimbursement);
  }

  @Override
  public void update(Reimbursement reimbursement) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.beginTransaction();
      session.merge(reimbursement);
      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}