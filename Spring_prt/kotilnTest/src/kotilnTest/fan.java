//package kotilnTest;
//
//public class fan {
//	private static int ON = 0;
//	private static int OFF = 1;
//	private static int WORKING = 2;
//	private boolean power_exist;
//	private int state;
//	
//	public fan(boolean power_exist){
//		state = OFF;
//		this.power_exist = power_exist;
//	}
//	
//	public void switch_on(){
//		if(power_exist == true && state == OFF){
//			state = ON;
//			turnon();
//		}
//	}
//	
//	public void switch_off() {
//		if(state == ON || state == WORKING){
//			state = OFF;
//			turnoff();
//		}
//	}
//	
//	public void run(){
//		if(state == ON){
//			operate();
//		}
//	}
//	
//	public void stop(){
//		if(state == WORKING){
//			suspend();
//		}
//	}
//}
