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
    String globalPosition[]; 
    int globalIndice;
    
    int TURN_45_ANGLE = 360; // Deveria ser 360, erro statico de 10
    int TURN_90_ANGLE = 540; // Valor pego no empirismo
	int MOVE_1_STEP_ANGLE = 850;
	
    Robo(int x, int y){
    	us = new UltrasonicSensor(SensorPort.S4);
    	motorA = Motor.A;
    	motorB = Motor.B;
    	motorC = Motor.C;
    	motorA.setSpeed(100);
    	globalPosition = new String[]{"N", "E", "S", "W"};
    	globalIndice = 0;
    	pos = new Position(x,y);
    	direction = "N";
    }
    
    public void move(Node no, Boolean flag) {
    	//int x, y;
    	//x = no.pos.x - pos.x; y = no.pos.y - pos.y;
    	if (flag) {
    		globalIndice = 2;
    		this.pos = no.pos;
    		S();
    		System.out.println("x: "+no.pos.x+" y:"+no.pos.y+" cust:"+no.f);
    		return;
    	}
    	//System.out.println("x: "+no.pos.x+" y:"+no.pos.y+" cust:"+no.f);
    	if(!no.pos.equals(pos)) {
    		if(no.getDirection().equals("E")) {
    			if (direction.equals("W")) {
    				this.E();
    				globalIndice++;
    				this.E();
    				globalIndice++;
    			}else {
    				this.E();
    				globalIndice++;
    			}
    			//this.pos.x++;
    		}
    		
    		
    		if(no.getDirection().equals("W")) {
    			if (direction.equals("E")) {
    				this.W();
    				globalIndice--;
    				this.W();
    				globalIndice--;
    			}else {
    				this.W();
    				globalIndice--;
    			}
    			//this.pos.x--;
    		}
    		
    	//}
    	this.moveForward();
    	}
    	
    	
    		
    	setPos();
    	direction = no.getDirection();
    	//pos = no.getPosition();    	
    }
    
    public void setPos() {   			
    	int index = ((globalIndice%4)+4)%4;
    	if (globalPosition[index].equals("N")) {
    		this.pos.y++;
    		//direction = "N";
		}
    	if (globalPosition[index].equals("W")) {
	    		this.pos.x--;
	    		///direction = "W";
    	}
		if (globalPosition[index].equals("E")) {
		    		this.pos.x++;
		    		//direction = "E";
		}
		if (globalPosition[index].equals("S")) {
    		this.pos.y--;
    		//direction = "S";
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
    
    //SUL
    public Boolean S() {
    	motorB.rotateTo((TURN_90_ANGLE*2)-70, true);
    	motorC.rotateTo(((TURN_90_ANGLE*2)-70)*-1, true);
    	
    	while (motorB.isMoving() || motorC.isMoving());
    	
    	System.out.println("SOUTH");
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
    public Boolean lookFront() {
    	motorA.rotateTo(0, true);

    	int distance = 0;
    	while (motorA.isMoving());
    	Delay.msDelay(500);
    	distance = us.getDistance();
    	System.out.println("Front:" + distance);
    	
    	return distance > 20 && distance < 200;
    }
    
    //Return true if step is free
    public Boolean lookLeft() {
    	motorA.rotateTo(91, true);
    	
    	int distance = 0;
    	while (motorA.isMoving());
    	Delay.msDelay(500);
    	distance = us.getDistance();
    	System.out.println("Left:" + distance);

    	return distance > 20 && distance < 200;
    }
    
    //Return true if step is free
    public Boolean lookRight() {
    	motorA.rotateTo(-91, true);
 	
    	int distance = 0;
    	while (motorA.isMoving());
    	Delay.msDelay(500);
    	distance = us.getDistance();
    	System.out.println("Right:" + distance);

    	return distance > 20 && distance < 200;
}
}
