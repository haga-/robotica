import lejos.nxt.Button;

public class HelloWorld {
	static Position initial_pos = new Position(3,1);
	static Position final_pos = new Position(3,5);
	static Map map = new Map(initial_pos, final_pos);
	static AStar astar = new AStar(initial_pos, final_pos);
	
    public static void main(String[] args) {
    	
        Robo robo = new Robo(initial_pos.x, initial_pos.y);
        
        while(!robo.pos.equals(final_pos)) {
        	// Direita
    		System.out.println("NO Right");
    		Position pos = new Position(robo.pos.x+1, robo.pos.y);
    		Block b = new Block(astar.G(pos), astar.H(pos), pos, robo.lookRight());
    		map.setMap(b);
        	
    		// Esquerda
    		System.out.println("NO Left");
    		pos = new Position(robo.pos.x-1, robo.pos.y);
    		b = new Block(astar.G(pos), astar.H(pos), pos, robo.lookLeft());
    		map.setMap(b);
        	
    		// Frente
    		System.out.println("NO Front");
    		pos = new Position(robo.pos.x, robo.pos.y+1);
    		b = new Block(astar.G(pos), astar.H(pos), pos, robo.lookFront());
    		map.setMap(b);        	
        	
        }
        System.out.println("CHEGOU!!!");
        Button.waitForAnyPress();
    }
    
}

