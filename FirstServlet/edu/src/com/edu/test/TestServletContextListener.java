package com.edu.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class TestServletContextListener implements ServletContextListener{
	public TestServletContextListener() {
		System.out.println("TestServletContextListener 객체 생성");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("TestServletContextListener 객체 초기화");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("TestServletContextListener 객체 해제");		
	}
}
