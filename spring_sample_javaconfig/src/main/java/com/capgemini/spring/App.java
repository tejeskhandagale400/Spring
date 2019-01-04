package com.capgemini.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.capgemini.spring.renderer.MessageRenderer;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);

		MessageRenderer renderer = appContext.getBean("result", MessageRenderer.class);
		renderer.render();

	}
}
