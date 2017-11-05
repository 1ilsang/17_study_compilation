package com.springbook.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {
	
	public static void main(String[] args) {
		//1. Spring �����̳� ����
		AbstractApplicationContext container =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. Spring �����̳ʷκ��� UserServiceImpl ��ü�� LookUp
		UserService userService = (UserService) container.getBean("userService");
		
		//3. �α��� ��� �׽�Ʈ
		UserVO vo = new UserVO();
		vo.setId("test");
		
		
		UserVO user = userService.getUser(vo);
		
		if(user != null){
			System.out.println(user.getName() + "�� ȯ���մϴ�.");
		}else{
			System.out.println("�α��� ����");
		}
		
		//4.spring �����̳ʸ� ����
		container.close();
	}
}
