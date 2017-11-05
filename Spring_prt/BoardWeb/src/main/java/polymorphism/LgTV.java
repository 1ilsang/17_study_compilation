package polymorphism;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")
public class LgTV implements TV {
	/*@Autowired
	@Qualifier("sony")*/
	//���� ����� Resource �� ����ϸ� ����
	//@Resource(name="apple")
	
	//������̼ǰ� xml ������ ������ ����ϴ� ���
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
