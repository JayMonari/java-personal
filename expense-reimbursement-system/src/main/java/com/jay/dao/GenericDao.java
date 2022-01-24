package com.jay.dao;

public interface GenericDao<T, ID> {
  public void insert(T t);
}
