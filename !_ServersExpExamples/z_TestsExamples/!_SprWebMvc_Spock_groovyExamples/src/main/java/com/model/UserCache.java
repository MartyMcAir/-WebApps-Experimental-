package com.model;

/**
 * @author Ivan Khalopik
 */
public interface UserCache {
  User getUser(String name);

  void invalidate();
}