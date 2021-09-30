package com.model;

import java.util.List;

/**
 * @author Ivan Khalopik
 */
public interface UserDAO {

  List<User> findAll();
}