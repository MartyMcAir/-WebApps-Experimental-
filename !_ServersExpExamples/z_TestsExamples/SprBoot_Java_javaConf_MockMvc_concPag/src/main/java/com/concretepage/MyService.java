package com.concretepage;

public class MyService {
	public String getMessage(String name) {
		return "Hello " + name;
	}
	public String saveUser(String user) {
      System.out.println(user);
      return "success";
	}
}
