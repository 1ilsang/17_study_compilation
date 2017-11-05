package polymorphism;

import org.springframework.stereotype.Component;

//@Component("apple")
public class AppleSpeaker implements Speaker {

	public AppleSpeaker() {
		System.out.println("===> AppleSpeaker Object Create");
	}

	@Override
	public void volumeUp() {
		System.out.println("AppleSpeaker -- sound Up");
	}

	@Override
	public void volumeDown() {
		System.out.println("AppleSpeaker -- sound Down");

	}

}
