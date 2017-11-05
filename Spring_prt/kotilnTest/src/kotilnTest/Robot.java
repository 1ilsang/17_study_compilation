package kotilnTest;

public abstract class Robot {
	  private String name;
	  private AttackStrategy attackStrategy;
	  private MovingStrategy movingStrategy;

	  public Robot(String name){
	    this.name = name;
	  }
	  public String getName(){
	    return name;
	  }
	  public void move(){
	    movingStrategy.move();
	  }
	  public void attack(){
	    attackStrategy.attack();
	  }
	  public void setMovingStrategy(MovingStrategy movingStrategy){
	    this.movingStrategy = movingStrategy;
	  }
	  public void setAttackStrategy(AttackStrategy attackStrategy){
	    this.attackStrategy = attackStrategy;
	  }
	}