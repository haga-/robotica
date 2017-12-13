import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;
import java.util.ArrayList;

public class Robo {
	private UltrasonicSensor us;
	private NXTRegulatedMotor motorA;
    private NXTRegulatedMotor motorB;
    private NXTRegulatedMotor motorC;
    public Position pos; 
    int direction;
    int globalPosition;
    
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
    	globalPosition = 0;
    	direction = 0;
    }
    
    public void move(Block blk) {
    	int x, y;
    	x = blk.pos.x - pos.x; y = blk.pos.y - pos.y;
    	while(!pos.equals(blk.pos)) {
    		
    	}
    	
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
    
    public Boolean moveForward() {
    	motorB.resetTachoCount();
    	motorC.resetTachoCount();
    	
    	Motor.B.rotate(MOVE_1_STEP_ANGLE, true);
    	Motor.C.rotate(MOVE_1_STEP_ANGLE, true);
    	
    	while (motorB.isMoving() || motorC.isMoving());
    	
    	return true;
    }
    
    //Return true if step is free
    public Boolean lookFront() {
    	motorA.rotateTo(0, true);

    	int distance = 0;
    	while (motorA.isMoving());
    	Delay.msDelay(500);
    	distance = us.getDistance();
    	System.out.println("Front:" + distance);

    	return distance > 20 && distance < 80;
    }
    
    //Return true if step is free
    public Boolean lookLeft() {
    	motorA.rotateTo(91, true);
    	
    	int distance = 0;
    	while (motorA.isMoving());
    	Delay.msDelay(500);
    	distance = us.getDistance();
    	System.out.println("Left:" + distance);

    	return distance > 20 && distance < 80;
    }
    
    //Return true if step is free
    public Boolean lookRight() {
    	motorA.rotateTo(-91, true);
 	
    	int distance = 0;
    	while (motorA.isMoving());
    	Delay.msDelay(500);
    	distance = us.getDistance();
    	System.out.println("Right:" + distance);

    	return distance > 20 && distance < 80;
}
}
