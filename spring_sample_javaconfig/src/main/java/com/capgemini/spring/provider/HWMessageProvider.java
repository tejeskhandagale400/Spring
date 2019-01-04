package com.capgemini.spring.provider;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component("HW")
@Primary
public class HWMessageProvider implements MessageProvider {

	public String getMessage() {
		// TODO Auto-generated method stub
		return "Hello World!";
	}

}
