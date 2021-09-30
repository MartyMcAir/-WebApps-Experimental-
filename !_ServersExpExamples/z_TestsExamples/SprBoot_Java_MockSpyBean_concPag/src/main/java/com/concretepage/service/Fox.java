package com.concretepage.service;

import org.springframework.stereotype.Component;

@Component("fox")
public class Fox implements Animal {
  @Override
  public String getName() {
	return "Fox";
  }
}
