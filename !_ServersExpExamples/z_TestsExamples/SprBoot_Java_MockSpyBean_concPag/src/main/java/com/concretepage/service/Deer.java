package com.concretepage.service;

import org.springframework.stereotype.Component;

@Component("deer")
public class Deer implements Animal {
  @Override
  public String getName() {
	return "Deer";
  }
}