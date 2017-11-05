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
	
	//2. Spring 컨테이너에게 필요한 객체를 요청(lookup)
	TV tv = (TV) factory.getBean("tv");
//	TV tv2 = (TV) factory.getBean("tv");
	//동일 객체를 여러개 생성해도 appContext.xml 에서 
	//scope를 singleton 으로 주었으므로 메모리엔 하나만 생성
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