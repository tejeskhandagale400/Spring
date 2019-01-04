package com.capgemini.spring.provider;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
@Component("GM")
public class GMMessageProvider implements MessageProvider {

	public String getMessage() {
		return "Good Morning!";
	}

}
