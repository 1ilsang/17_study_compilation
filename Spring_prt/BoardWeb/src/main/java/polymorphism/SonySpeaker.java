package polymorphism;

import org.springframework.stereotype.Component;

//@Component("sony")
public class SonySpeaker implements Speaker{
	public SonySpeaker() {
		System.out.println("===> SonySpeaker Object Create");
	}

	public void volumeUp() {
		System.out.println("SonySpeaker --- sound Up");
	}

	public void volumeDown() {
		System.out.println("SonySpeaker --- sound Down");
	}
}
