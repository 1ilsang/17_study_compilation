package polymorphism;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")
public class LgTV implements TV {
	/*@Autowired
	@Qualifier("sony")*/
	//위의 방법을 Resource 를 사용하면 쉽게
	//@Resource(name="apple")
	
	//어노테이션과 xml 설정을 병행해 사용하는 방법
	@Autowired
	private Speaker speaker;
	
	public void setSpeaker(Speaker speaker) {
		System.out.println("===> setSpeaker() Call");
		this.speaker = speaker;
	}
	public void powerOn() {
		System.out.println("LgTV - turn On");
	}

	public void powerOff() {
		System.out.println("LgTV - turn Off");

	}

	public void volumeUp() {
		speaker.volumeUp();
	}

	public void volumeDown() {
		speaker.volumeDown();
	}
}
