package com.qa.demo;

import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
	    Object byName = context.getBean("greeting");
	    String byType = context.getBean(String.class);
	    String byBoth = context.getBean("greeting", String.class);
	    
	    Object timeName = context.getBean("localTime");
	    LocalTime timeType = context.getBean(LocalTime.class);
	    LocalTime timeBoth = context.getBean("localTime", LocalTime.class);
	    
	    
	    
	    System.out.println(byName);
	    System.out.println(byType);
	    System.out.println(byBoth);
	    
	    System.out.println(timeName);
	    System.out.println(timeType);
	    System.out.println(timeBoth);
	}
	
	  @Bean
	    public String greeting() {
	        return "Hello, World";
	    }
	  
	  @Bean
	    public LocalTime localTime() {
	        return LocalTime.now();
	    }
}