package com.capgemini.spring.renderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.capgemini.spring.provider.MessageProvider;

@Service("consoleMessageRenderer")
public class ConsoleMessageRenderer implements MessageRenderer {
	
	//@Autowired
	private MessageProvider provider;
	
	@Autowired
	public ConsoleMessageRenderer(MessageProvider provider) {
		System.out.println("in constructor");

		this.provider = provider;
	}
	public ConsoleMessageRenderer() {
	}

	//@Autowired
	public void setProvider(MessageProvider provider) {
		System.out.println("in setter method");
		this.provider = provider;
	}

	public void render() {
		System.out.println(provider.getMessage());
	}
}
