package com.capgemini.spring.renderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.capgemini.spring.provider.MessageProvider;

public class ConsoleMessageRenderer implements MessageRenderer {

	private MessageProvider provider;
	
	
	public ConsoleMessageRenderer(MessageProvider provider) {
		System.out.println("Using  constructor injection");
		this.provider = provider;
	}

	public ConsoleMessageRenderer() {
		System.out.println("Using  No argument constructor injection");

	}
	@Autowired 
	public void setProvider(MessageProvider provider) {
		System.out.println("Using setter Injection");
		this.provider = provider;
	}

	public void render() {
		System.out.println(provider.getMessage());
	}
}
