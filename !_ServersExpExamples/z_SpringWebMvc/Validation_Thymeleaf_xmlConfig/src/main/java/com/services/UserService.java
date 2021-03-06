package com.services;

import java.util.List;

import com.dao.UserDAO;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
    private UserDAO userDAO;
	
	
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
	
	public void save(User aUser) {
		userDAO.save(aUser);
	}
	
	public void delete(int userId) {
		userDAO.delete(userId);
	}

}
