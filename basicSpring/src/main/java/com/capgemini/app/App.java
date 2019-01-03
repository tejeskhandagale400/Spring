package com.capgemini.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.app.bean.HelloWorld;
import com.capgemini.app.bean.Organization;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		/*
		 * HelloWorld objA = (HelloWorld) context.getBean("helloWorld");
		 * 
		 * objA.setMessage("I'm object A"); objA.getMessage(); HelloWorld objB =
		 * (HelloWorld) context.getBean("helloWorld"); objB.setMessage("replace");
		 * objB.getMessage(); objA.getMessage();
		 */
         Organization org =  (Organization) context.getBean("organization");
        //System.out.println(org.getBoardMembers());
       // System.out.println(org.toString());
        System.out.println(org.getIpAddresses());

    }
}
