package com.ip.itest.pattern.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeMain {
	
	 public static void main(String[] args) {
	      ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
	      HelloWorld objA = (HelloWorld) context.getBean("helloWorld");    
	      objA.setMessage("I'm object A");
	      objA.getMessage();

	      HelloWorld objB = (HelloWorld) context.getBean("helloWorld2");
	      objB.setMessage("I'm object B");
	      objB.getMessage();
	   }
}
