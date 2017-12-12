import java.util.*;
import lejos.nxt.Button;

public class HelloWorld {
	static int init_x = 3;
	static int init_y = 1;
	static Position initial_pos = new Position(3,1);
	static Position final_pos = new Position(3,5);
	
    public static void main(String[] args) {
    	
        Robo robo = new Robo(init_x,init_y);
        //AStar astar = new AStar(final_x, final_y);
        AStar astar = new AStar(initial_pos, final_pos);
        //ArrayList<Node> nodes = new ArrayList<Node>(); 
        PriorityQueue nodes = new PriorityQueue();
        
        while(!robo.pos.equals(final_pos)) {
        	if(robo.lookRight() > 20 ) {
        		System.out.println("NO Right");
        		Node no = new Node(new Position(robo.pos.getX()+1, robo.pos.getY()), "E"); 

        		no.setG(astar.G(no.pos));
        		no.setH(astar.H(no.pos));
        		no.setF();
        		nodes.add(no);        		
        	}
        	
        	if(robo.lookLeft() > 20 ) {
        		System.out.println("NO Left");
        		Node no = new Node(new Position(robo.pos.getX()-1, robo.pos.getY()), "W"); 
        		
        		no.setG(astar.G(no.pos));
        		no.setH(astar.H(no.pos));
        		no.setF();
        		nodes.add(no);
        	}
        	
        	if(robo.lookFront() > 20) {
        		System.out.println("NO Front");
        		Node no = new Node(new Position(robo.pos.getX(), robo.pos.getY()+1), "N"); 
        		
        		no.setG(astar.G(no.pos));
        		no.setH(astar.H(no.pos));
        		no.setF();
        		nodes.add(no);
        	}
        	
        	//TODO: SOLUCAO PALEATIVA BUSCA MENOR CUSTO
        	/*int posicao_no = 0;
        	for (int i = 0; i < nodes.size(); i++) {
            	if ((nodes.get(i).getF() < nodes.get(posicao_no).getF()) && !nodes.get(i).getChoice()) {
            		posicao_no = i;
            	}
            }*/
        	Node node = nodes.first();
        	node.setChoice(true);
        	robo.move(node);            
        
        }
        System.out.println("CHEGOU!!!");
        Button.waitForAnyPress();
    }
    
}

