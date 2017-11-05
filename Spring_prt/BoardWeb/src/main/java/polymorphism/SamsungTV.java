package polymorphism;

public class SamsungTV implements TV {
	private Speaker speaker;
	private int price;

	public SamsungTV() {
		System.out.println("===> SamsunTV Objdect Create");
	}

	public void setPrice(int price) {
		System.out.println("===> setPrice() Call");
		this.price = price;
	}

	public void setSpeaker(Speaker speaker) {
		System.out.println("===> setSpeaker() Call");
		this.speaker = speaker;
	}

	// ������ ������
	public SamsungTV(Speaker speaker) {
		System.out.println("===> SamsunTV(2) Objdect Create");
		this.speaker = speaker;
	}

	public SamsungTV(Speaker speaker, int price) {
		System.out.println("===> SamsunTV(3) Objdect Create");
		this.speaker = speaker;
		this.price = price;
	}

	public void initMethod() {
		System.out.println("��ü �ʱ�ȭ �۾� ó��..");
	}

	public void destroyMethod() {
		System.out.println("��ü ���� ���� ó���� ����");
	}

	public void powerOn() {
		System.out.println("SamsungTV - Power On. (price = " + price + ")");
	}

	public void powerOff() {
		System.out.println("SamsungTV - Power Off");
	}

	public void volumeUp() {
		speaker.volumeUp();
	}

	public void volumeDown() {
		speaker.volumeDown();
	}
}
