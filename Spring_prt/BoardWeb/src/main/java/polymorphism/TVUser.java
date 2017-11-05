/*package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
public static void main(String[] args) {
	//Factory Design Pattern
	BeanFactory factory = new BeanFactory();
	TV tv = (TV)factory.getBean(args[0]);
	tv.powerOn();
	tv.volumeUp();
	tv.volumeDown();
	tv.powerOff();
	
	//1.Spring Container Start (Dependency Lookup)
	AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
	
	//2. Spring �����̳ʿ��� �ʿ��� ��ü�� ��û(lookup)
	TV tv = (TV) factory.getBean("tv");
//	TV tv2 = (TV) factory.getBean("tv");
	//���� ��ü�� ������ �����ص� appContext.xml ���� 
	//scope�� singleton ���� �־����Ƿ� �޸𸮿� �ϳ��� ����
	//TV tv3 = (TV) factory.getBean("tv");
	tv.powerOn();
	tv.volumeUp();
	tv.volumeDown();
	tv.powerOff();

	//3. Exit Spring Container
	factory.close();
}
}


*/