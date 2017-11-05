package kotilnTest;

public class Client{
	public static void main(String[] args) {
	    Robot t = new TaekwonV("T");
	    Robot a = new Atom("A");

	    t.setMovingStrategy(new WalkingStrategy());
	    t.setAttackStrategy(new MissileStrategy());
	    a.setMovingStrategy(new FlyingStrategy());
	    a.setAttackStrategy(new PunchStrategy());

	    t.move();
	    t.attack();
	    a.move();
	    a.attack();
		Light light = new Light();
		light.off_button_pushed();
		light.on_button_pushed();
		light.off_button_pushed();
	}
}
