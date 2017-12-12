import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;

public class Robo {
	private UltrasonicSensor us;
	private NXTRegulatedMotor motorA;
    private NXTRegulatedMotor motorB;
    private NXTRegulatedMotor motorC;
    public Position pos; 
    String direction;
    
    int TURN_45_ANGLE = 360; // Deveria ser 360, erro statico de 10
    int TURN_90_ANGLE = 565; // Valor pego no empirismo
	int MOVE_1_STEP_ANGLE = 740;
    
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
    	int x, y;
    	x = no.pos.x - pos.x; y = no.pos.y - pos.y;
    	
    	System.out.println("CHOICE "+no.getDirection());
    	if (direction == no.getDirection()) {
    		this.moveForward();
        	System.out.println("CHOICE "+no.getDirection());
    	}else {
    		if(no.getDirection().equals("E"))
    			this.E();
    		if(no.getDirection().equals("N"))
    			this.N();
    		if(no.getDirection().equals("W"))
    			this.W();
    		this.moveForward();
    	}
    	direction = no.getDirection();
    	pos = no.getPosition();    	
    }
    
    //LESTE
    public Boolean E() {
    	motorB.resetTachoCount();
    	motorC.resetTachoCount();
    	motorB.rotateTo(TURN_90_ANGLE, true);
    	motorC.rotateTo(-TURN_90_ANGLE, true);
    	
    	while (motorB.isMoving() || motorC.isMoving());
    	System.out.println("RIGHT");
    	return true;
    }
    //OESTE
    public Boolean W() {
    	motorB.resetTachoCount();
    	motorC.resetTachoCount();
    	motorB.rotateTo(-TURN_90_ANGLE, true);
    	motorC.rotateTo(TURN_90_ANGLE, true);
    	
    	
    	while (motorB.isMoving() || motorC.isMoving());
    	
    	System.out.println("LEFT");
    	
    	return true;
    }
    
    //NORTE
    public Boolean N() {
    	motorB.resetTachoCount();
    	motorC.resetTachoCount();
    	motorB.rotateTo(0, true);
    	motorC.rotateTo(0, true);
    	
    	while (motorB.isMoving() || motorC.isMoving());
    	
    	System.out.println("FRONT");
    	return true;
    }
    
    //SUL
    /*public Boolean S() {
    	motorB.rotateTo((TURN_90_ANGLE*2)-70, true);
    	motorC.rotateTo(((TURN_90_ANGLE*2)-70)*-1, true);
    	
    	while (motorB.isMoving() || motorC.isMoving());
    	
    	System.out.println("FRONT");
    	return true;
    }*/
    
    public Boolean moveForward() {
    	motorB.resetTachoCount();
    	motorC.resetTachoCount();
    	
    	Motor.B.rotate(MOVE_1_STEP_ANGLE, true);
    	Motor.C.rotate(MOVE_1_STEP_ANGLE, true);
    	
    	while (motorB.isMoving() || motorC.isMoving());
    	
    	return true;
    }
    /*
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
    }*/
    //Return true if step is free
    public int lookFront() {
    	motorA.rotateTo(0, true);

    	int distance = 0;
    	while (motorA.isMoving());
    	Delay.msDelay(1000);
    	distance = us.getDistance();
    	System.out.println("Front:" + distance);

    	return distance;
    }
    
    //Return true if step is free
    public int lookLeft() {
    	motorA.rotateTo(91, true);
    	
    	int distance = 0;
    	while (motorA.isMoving());
    	Delay.msDelay(1000);
    	distance = us.getDistance();
    	System.out.println("Left:" + distance);

    	return distance;
    }
    
    //Return true if step is free
    public int lookRight() {
    	motorA.rotateTo(-91, true);
 	
    	int distance = 0;
    	while (motorA.isMoving());
    	Delay.msDelay(1000);
    	distance = us.getDistance();
    	System.out.println("Right:" + distance);

    	return distance;
}
}
