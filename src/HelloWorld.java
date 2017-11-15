import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.Color;
import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;


public class HelloWorld {
    /**
     * @param args
     */
    public static void main(String[] args) {
        ColorSensor cs = new ColorSensor(SensorPort.S3);
        UltrasonicSensor us = new UltrasonicSensor(SensorPort.S4);
        NXTRegulatedMotor motorB = Motor.B;
        NXTRegulatedMotor motorC = Motor.C;
        int curr_color, last_color, transitions = 0;
        double distance = 0;
        curr_color = last_color = cs.getColor().getColor();
        
        while (transitions < 18) {
        	motorB.forward();
        	motorC.forward();
        	// Branco 0,5cm; Preto 1,5cm
        	curr_color = cs.getColor().getColor();
        	if(curr_color != last_color) {
        		transitions++;
        		distance += curr_color == Color.WHITE ?  1.5 : 0.5; 
        		last_color = curr_color;
        	}
        	
        	System.out.println("UltraSonic distance: " + us.getDistance());
        	System.out.println("Distância: " + distance);
            
        }
        
        Button.waitForAnyPress();
        
    }
}
