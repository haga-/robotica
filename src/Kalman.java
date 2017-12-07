import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.Color;

public class Kalman {
    /**
     * @param args
     */
    public static void Kalman(String[] args) {
        ColorSensor cs = new ColorSensor(SensorPort.S3);
        UltrasonicSensor us = new UltrasonicSensor(SensorPort.S4);
        NXTRegulatedMotor motorB = Motor.B;
        NXTRegulatedMotor motorC = Motor.C;
        
        cs.calibrateHigh();
        
        //Odometro(motorB, motorC, cs);
        //Ultrasson(motorB, motorC, us);
        FiltrodeKalman(motorB, motorC, cs, us);
        
        /* Odometro 
       
        int curr_color, last_color, transitions = 0;
        double distance = 0;
        curr_color = last_color = cs.getColor().getColor();
        
        while (distance <= 100) {
        	motorB.forward();
        	motorC.forward();
        	// Branco 0,5cm; Preto 1,5cm
        	curr_color = cs.getColor().getColor();
        	if(curr_color != last_color) {
        		transitions++;
        		distance += curr_color == Color.WHITE ?  0.4 : 1; 
        		last_color = curr_color;
        	}
        	System.out.println("Transicoes: " + transitions);
            
        }*/
        
        /* Sensor ultrassonico 
        int distance = us.getDistance(); 
        while(distance > 50) { //103 == 100 cm reais
        	motorB.forward();
        	motorC.forward();
        	distance = us.getDistance();
        	System.out.println(distance);
        }*/
        
        
    }
    
    public static void Odometro(NXTRegulatedMotor motorB, NXTRegulatedMotor motorC, ColorSensor cs){
    	int curr_color, last_color, transitions_white = 0, transitions_black = 0, flagColor = 0;
        double distance = 0;
        curr_color = last_color = cs.getColor().getColor();
        if (curr_color == Color.WHITE) {
        	flagColor = 0;
        	System.out.println("Começa em Branco");
        }else {
        	flagColor = 1;
        	System.out.println("Começa em Preto");
        }
        System.out.println("Pressione o Botão para Iniciar");
        //Button.waitForAnyPress();
        //motorB.setSpeed(200);
        //motorC.setSpeed(200); 
        motorB.forward();
    	motorC.forward();
        while (distance <= 100) {
        	// Branco 0,5cm; Preto 1,5cm
        	curr_color = cs.getColor().getColor();
        	if(curr_color != last_color) {
        		if (flagColor == 0) { //Mudou de branco para preto, andou 0,4cm;
        			transitions_white++;
        			distance += 0.4;
        			flagColor = 1;
        		}else { //Mudou de preto para branco, andou 1cm;
        			transitions_black++;
    				distance += 1;
    				flagColor = 0;
        		}
        		last_color = curr_color;
        		System.out.println("DT: " + ((int)distance));
        	}          
        }
        System.out.println("white: " + transitions_white + "black:" + transitions_black);
        motorB.stop(true);
        motorC.stop();             
        Button.waitForAnyPress();  
    }
    
    public static void Ultrasson(NXTRegulatedMotor motorB, NXTRegulatedMotor motorC, UltrasonicSensor us){
    	 int curr_color, last_color, transitions = 0, flag = 0;
         double distance = 0,  deslocamento_us, distance_us, distance_before_us = us.getDistance();
         System.out.println("Distancia da Parede: "+ distance_before_us);
         System.out.println("Pressione o Botão para Iniciar");
         Button.waitForAnyPress();
         motorB.setSpeed(200);
         motorC.setSpeed(200); 
         motorB.forward();
     	 motorC.forward();
         while(distance < 100) { //103 == 100 cm reais
        	
        	distance_us = us.getDistance();
    		//if ((distance_us <= 150) && (flag == 0) ) { //Executa só uma vez quando Ultrason começar a funcionar
    		//	distance_before_us = distance_us;
    		//	flag = 1;
    		//}  		
    		deslocamento_us = distance_before_us - distance_us;
    		if ((deslocamento_us > 0) && (deslocamento_us < 10)) {
    				System.out.println("US: " + deslocamento_us);
    				distance += deslocamento_us; //Filtro de Kalman linear
    				distance_before_us = distance_us;
    				System.out.println("DT: " + ((int)distance));    			
    		}
        }
        motorB.stop(true);
        motorC.stop();             
        Button.waitForAnyPress();
    }

    /* Filtro de Kalman Variaveis necessárias
     * variancia_us = 0.67
     * variancia_cs = 5.56
     * covariancia = 0.43
     * desvio_padrao_us = 0.74
     * desvio_padrao_cs = 1.99
     * k = variancia_cs / variancia_cs - variancia_us
     * estimativa_distancia = k*us.getDistance() + (1 - k)*cs.getDistance()
     * estimativa_erro = (k^2)*variancia_us + ((1 - k)^2)*variancia_cs
     * Rodando depois da primeira vez:
     * k = estimativa_erro_anterior / estimativa_erro_anterior + desvio_padrao
     * estimativa_final = estimativa_distancia_anterior + k * (estimativa_distancia - estimativa_distancia_anterior)
     * estimativa_erro = (1-k)*estimativa_erro_anterior 
     */
    public static void FiltrodeKalman(NXTRegulatedMotor motorB, NXTRegulatedMotor motorC, ColorSensor cs,  UltrasonicSensor us) {
    	while(true) {
        int curr_color, last_color, transitions_black = 0, transitions_white = 0;
        double distance = 0, distance_cs = 0,  deslocamento_us, distance_us, distance_before_us = us.getDistance();
        curr_color = last_color = cs.getColor().getColor();
        double K, P = 0.17;
        double desvio_padrao_medio = 2.36;  //(1.99 + 0.74) / 2; //1.37
        double estimativa_distancia = 0, estimativa_distancia_anterior = 0, estimativa_distancia_final = 0;
        int flag = 0;
        int flagColor = 0;
        
        if (curr_color == Color.WHITE) {
        	flagColor = 0;
        	System.out.println("Começa em Branco");
        }else {
        	flagColor = 1;
        	System.out.println("Começa em Preto");
        }
        System.out.println("Distancia da Parede: "+ distance_before_us);
        System.out.println("Pressione o Botão para Iniciar");
        
        //motorB.setSpeed(200);
        //motorC.setSpeed(200);
        motorB.forward();
    	motorC.forward();
        while (distance <= 100) { //140 == 1 metro, 280 == 2 metros
            
        	curr_color = cs.getColor().getColor();
        	if(curr_color != last_color) {
        		if (flagColor == 0) { //Mudou de branco para preto, andou 0,4cm;
        			transitions_white++;
        			distance_cs += 0.4;
        			flagColor = 1;
        			//System.out.println("Preto");
        		}else { //Mudou de preto para branco, andou 1cm;
        			transitions_black++;
    				distance_cs += 1;
    				flagColor = 0;
    				//System.out.println("Branco");
        		}
        		last_color = curr_color;
        	}
        		distance_us = us.getDistance();
        		if ((distance_us <= 150) && (flag == 0) ) { //Executa só uma vez quando Ultrason começar a funcionar
        			distance_before_us = distance_us;
        			flag = 1;
        		}
        		
        		deslocamento_us = distance_before_us - distance_us;
        		if ((deslocamento_us > 0) && (deslocamento_us < 10)) {
        			if(distance_cs > 0) {
        				System.out.println("CS: " + distance_cs);
        				System.out.println("US: " + deslocamento_us);
        				distance += P*distance_cs + (1 - P)*deslocamento_us; //Filtro de Kalman linear
        				distance_before_us = distance_us;
        				distance_cs = 0;
        				System.out.println("DT: " + ((int)distance));
        			}
        		}
        		
      		    //FILTRO DE KALMAN ITERATIVO - ESTIMATIVA DISTANCIA 
        		//estimativa_distancia = P*distance_cs + (1 - P)*deslocamento_us;
        		//System.out.print(", ED: "+((int) estimativa_distancia));
        		//Ganho de Kalman
        		//K = P / (P + desvio_padrao_medio);
        		//System.out.println("Ganho de Kalman: " + K);
        		//Distancia Real
        		//estimativa_distancia_final = estimativa_distancia_anterior + K * (estimativa_distancia - estimativa_distancia_anterior);
        		//System.out.println("Distancia Real: " + ((int)estimativa_distancia_final));
        		//Atualiza a Projeção de Erro
        		//P = (1 - K)*P;
        		//System.out.println("Projecao de erro: " + ((int)P));
        		//Restabelece as variaveis para o próximo loop
        		//distance += estimativa_distancia; //estimativa_distancia_final;
        		//estimativa_distancia_anterior = estimativa_distancia_final;
        		//System.out.println("DT: " + ((int)distance));
        		
        }     	      	           
        
        System.out.println("white: " + transitions_white + "black:" + transitions_black);
        motorB.stop(true);
        motorC.stop();
        
        Button.waitForAnyPress();
        }
    }
}
