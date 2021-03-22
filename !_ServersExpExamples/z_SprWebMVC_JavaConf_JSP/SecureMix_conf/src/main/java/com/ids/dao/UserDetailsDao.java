package com.ids.dao;


import com.ids.model.User;

public interface UserDetailsDao {
  User findUserByUsername(String username);
}
