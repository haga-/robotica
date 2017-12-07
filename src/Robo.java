import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class Robo {
	private UltrasonicSensor us;
	private NXTRegulatedMotor motorA;
    private NXTRegulatedMotor motorB;
    private NXTRegulatedMotor motorC;
    public Position pos; 
    String direction;
    
    int TURN_45_ANGLE = 360; // Deveria ser 360, erro statico de 10
    int TURN_90_ANGLE = 650; // Deveria ser 360, erro statico de 10
	int MOVE_1_STEP_ANGLE = 720;
    
    Robo(int x, int y){
    	us = new UltrasonicSensor(SensorPort.S4);
    	motorA = Motor.A;
    	motorB = Motor.B;
    	motorC = Motor.C;
    	motorA.setSpeed(100);
    	
    	pos = new Position(x,y);
    	direction = "N";
    }
    
    public void move(Node no) {
    	System.out.println("CHOICE "+no.getDirection());
    	if (direction == no.getDirection()) {
    		this.moveForward();
        	System.out.println("CHOICE "+no.getDirection());
    	}else {
    		if(no.getDirection() == "E")
    			E();
    		if(no.getDirection() == "N")
    			N();
    		if(no.getDirection() == "W")
    			W();
    		this.moveForward();
    	}
    	pos = no.getPosition();    	
    }
    
    //LESTE
    public Boolean E() {
    	motorB.rotateTo(TURN_90_ANGLE-70, true);
    	motorC.rotateTo((TURN_90_ANGLE-70)*-1, true);
    	
    	while (motorB.isMoving() || motorC.isMoving());
    	System.out.println("RIGHT");
    	return true;
    }
    //OESTE
    public Boolean W() {
    	motorC.rotateTo(TURN_90_ANGLE-100, true);
    	motorB.rotateTo((TURN_90_ANGLE-100)*-1, true);
    	
    	while (motorB.isMoving() || motorC.isMoving());
    	
    	System.out.println("LEFT");
    	
    	return true;
    }
    
    //NORTE
    public Boolean N() {
    	motorB.rotateTo(0, true);
    	motorC.rotateTo(0, true);
    	
    	while (motorB.isMoving() || motorC.isMoving());
    	
    	System.out.println("FRONT");
    	return true;
    }
    
    //SUL
    public Boolean S() {
    	motorB.rotateTo((TURN_90_ANGLE*2)-70, true);
    	motorC.rotateTo(((TURN_90_ANGLE*2)-70)*-1, true);
    	
    	while (motorB.isMoving() || motorC.isMoving());
    	
    	System.out.println("FRONT");
    	return true;
    }
    
    public Boolean moveForward() {
    	
    	Motor.B.rotate(MOVE_1_STEP_ANGLE, true);
    	Motor.C.rotate(MOVE_1_STEP_ANGLE, true);
    	
    	while (motorB.isMoving() || motorC.isMoving());
    	
    	return true;
    }
    
    public Boolean moveBackward() {
    	Motor.B.rotate(-MOVE_1_STEP_ANGLE, true);
    	Motor.C.rotate(-MOVE_1_STEP_ANGLE, true);
    	
    	while (motorB.isMoving() || motorC.isMoving());
    	
    	return true;
    }
    
    public Boolean moveRight(int vezes) {
    	E();
    	for(int i = 0; i < vezes; i++) {
    		this.moveForward();
    	}
    	return true;
    }
    
    public Boolean moveLeft(int vezes) {
    	W();
    	for (int i = 0; i < vezes; i++) {
    		return this.moveForward();
    	}
    	return true;
    }
    
    public Boolean moveNorth(int vezes) {
    	N();
    	for (int i = 0; i < vezes; i++) {
    		return this.moveForward();
    	}
    	return true;
    }
    
    public Boolean moveSouth(int vezes) {
    	S();
    	for (int i = 0; i < vezes; i++) {
    		return this.moveForward();
    	}
    	return true;
    }
    //Return true if step is free
    public int lookFront() {
    	motorA.rotateTo(0, true);
    	
    	while (motorA.isMoving());
    	
    	System.out.println("Front:" + us.getDistance());
//    	if (us.getDistance() < 20)
//    		return false;
    	
//    	return true;
    	return us.getDistance();
    }
    
    //Return true if step is free
    public int lookLeft() {
    	motorA.rotateTo(91, true);
    	
    	System.out.println("Left:" + us.getDistance());
    	
    	while (motorA.isMoving());
    	
//    	if (us.getDistance() < 20)
//		return false;
	
//	return true;
    	return us.getDistance();
    }
    
    //Return true if step is free
    public int lookRight() {
    	motorA.rotateTo(-91, true);
 	
    	while (motorA.isMoving());
    	
//    	if (us.getDistance() < 20)
//		return false;
	
//	return true;
    	return us.getDistance();
    }
}
