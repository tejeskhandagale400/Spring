package com.capgemini.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.capgemini.spring.provider.GMMessageProvider;
import com.capgemini.spring.provider.MessageProvider;
import com.capgemini.spring.renderer.ConsoleMessageRenderer;
import com.capgemini.spring.renderer.MessageRenderer;

@Configuration
@ComponentScan({ "com.capgemini.spring" })
public class AppConfig {

	/**
	 * using setter injection
	 * 
	 * @return
	 */
	/*
	 * @Bean(name = "result") public MessageRenderer getResult() { MessageRenderer
	 * renderer = new ConsoleMessageRenderer(); renderer.setProvider(getProvider());
	 * return renderer; }
	 */
	/**
	 * using constructor injection
	 * 
	 * @return
	 */
	/*
	 * @Bean(name = "result") public MessageRenderer getResult() { MessageRenderer
	 * renderer = new ConsoleMessageRenderer(getProvider()); return renderer; }
	 * 
	 * @Bean(name = "provider") public MessageProvider getProvider() { return new
	 * GMMessageProvider();
	 * 
	 * }
	 */

	
	
	/// for autowired
	/*
	 * @Bean(name = "result") public MessageRenderer getResult() { MessageRenderer
	 * renderer = new ConsoleMessageRenderer(); renderer.setProvider(getProvider());
	 * return renderer; }
	 */
	
	
	@Bean(name = "result")
	public MessageRenderer getResult() {
		MessageRenderer renderer = new ConsoleMessageRenderer();
		//renderer.setProvider(getProvider());
		return renderer;
	}

	/*
	 * @Bean(name = "provider") public MessageProvider getProvider() { return new
	 * GMMessageProvider();
	 * 
	 * }
	 */
}
