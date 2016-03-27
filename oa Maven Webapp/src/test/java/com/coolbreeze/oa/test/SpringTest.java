package com.coolbreeze.oa.test;

import org.jbpm.api.ProcessEngine;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

	@Test
	public void test(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:beans.xml");
		ProcessEngine processEngine=(ProcessEngine) ac.getBean("processEngine");
		System.out.println(processEngine);
	}
	
}
