//import lejos.nxt.Motor;
//import lejos.nxt.NXTRegulatedMotor;
//import lejos.nxt.UltrasonicSensor;
//import lejos.robotics.Color;
//import lejos.nxt.Button;
//import lejos.nxt.ColorSensor;
//import lejos.nxt.SensorPort;

import java.util.*;

import lejos.nxt.Button;

public class HelloWorld {
    /**
     * @param args
     */
	
	//int mMap[][] = new int[25][25];
	static int init_x = 5;
	static int init_y = 3;
	static int final_x = 3;
	static int final_y = 5;
	
    public static void main(String[] args) {
    	
        Robo robo = new Robo(init_x,init_y);
        AStar astar = new AStar(final_x, final_y);
        ArrayList<Node> nodes = new ArrayList<Node>();
        while(robo.pos.getX() != final_x && robo.pos.getY() != final_y) {
        	
        	if(robo.lookRight() > 20 ) {
        		System.out.println("NO Right");
        		Node no = new Node(new Position(robo.pos.getX(), robo.pos.getY()+1), "E"); 
        		no.setCost(astar.manhattan(no.pos.x, no.pos.y));
        		no.setNumberSteps(astar.calcNumberSteps(no.pos.x, no.pos.y));
        		nodes.add(no);        		
        	}
        	
        	if(robo.lookLeft() > 20 ) {
        		System.out.println("NO Left");
        		Node no = new Node(new Position(robo.pos.getX(), robo.pos.getY()-1), "W"); 
        		no.setCost(astar.manhattan(no.pos.x, no.pos.y));
        		no.setNumberSteps(astar.calcNumberSteps(no.pos.x, no.pos.y));
        		nodes.add(no);
        	}
        	
        	if(robo.lookFront() > 20) {
        		System.out.println("NO Front");
        		Node no = new Node(new Position(robo.pos.getX()+1, robo.pos.getY()), "N"); 
        		no.setCost(astar.manhattan(no.pos.x, no.pos.y));
        		no.setNumberSteps(astar.calcNumberSteps(no.pos.x, no.pos.y));
        		nodes.add(no);
        	}
        	
        	//TODO: SOLUCAO PALEATIVA BUSCA MENOR CUSTO
        	int posicao_no = 0;
        	for (int i = 0; i < nodes.size(); i++) {
            	if ((nodes.get(i).getCost() < nodes.get(posicao_no).getCost()) && !nodes.get(i).getChoice()) {
            		posicao_no = i;
            	}
            }
        	nodes.get(posicao_no).setChoice(true);
        	robo.move(nodes.get(posicao_no));            
        
        	Button.waitForAnyPress();
        }

        Button.waitForAnyPress();
        //robo.moveForward();
        /*int flag = 0;
        while(flag != 4) {
        	robo.moveForward();
        	flag++;
        }*/
    }
    
}

