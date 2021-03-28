package com.mkyong.dao;

import com.mkyong.model.User;

import java.util.List;

public interface UserDao {

	User findByName(String name);
	
	List<User> findAll();

}