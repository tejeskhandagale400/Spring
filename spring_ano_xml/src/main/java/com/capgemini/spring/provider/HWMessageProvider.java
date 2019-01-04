package com.capgemini.spring.provider;

import org.springframework.stereotype.Repository;

@Repository("message")
public class HWMessageProvider implements MessageProvider {

	public String getMessage() {
		// TODO Auto-generated method stub
		return "Hello World!";
	}

}
