package com.mkyong.dao;


import com.mkyong.model.User;

public interface UserDetailsDao {
  User findUserByUsername(String username);
}
