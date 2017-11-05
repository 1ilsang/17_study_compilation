package kotilnTest;

public class Light {
	private State state;
	public Light(){
		
	}
	public void  setState(State state) {
		this.state = state;
	}
	public void on_button_pushed(){
		state.on_button_pushed(this);
	}
	public void off_button_pushed() {
		state.off_button_pushed(this);
	}
}
