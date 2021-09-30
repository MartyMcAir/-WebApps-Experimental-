package com.concretepage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  MyService2 myService;
  
  public int getUserCount() {
	return myService.getCount();
  }
}