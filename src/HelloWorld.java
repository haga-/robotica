import lejos.nxt.Button;

public class HelloWorld {
	static Position initial_pos = new Position(3,1);
	static Position final_pos = new Position(3,5);
	static Map map = new Map(initial_pos, final_pos);
	static AStar astar = new AStar(initial_pos, final_pos);
	
    public static void main(String[] args) {
    	
        Robo robo = new Robo(initial_pos.x, initial_pos.y);
        
        while(!robo.pos.equals(final_pos)) {
        	if(robo.lookRight()) {
        		System.out.println("NO Right");
        		Position pos = new Position(robo.pos.x+1, robo.pos.y);
        		Block b = new Block(astar.G(pos), astar.H(pos), pos, true);
        		map.setMap(b);
        	} else {
        		Position pos = new Position(robo.pos.x+1, robo.pos.y);
        		Block b = new Block(-1, -1, pos, false);
        		map.setMap(b);
        	}
        	
        	if(robo.lookLeft()) {
        		System.out.println("NO Left");
        		Position pos = new Position(robo.pos.x-1, robo.pos.y);
        		Block b = new Block(astar.G(pos), astar.H(pos), pos, true);
        		map.setMap(b);
        	} else {
        		Position pos = new Position(robo.pos.x+1, robo.pos.y);
        		Block b = new Block(-1, -1, pos, false);
        		map.setMap(b);
        	}
        	
        	if(robo.lookFront() > 20) {
        		System.out.println("NO Front");
        		Position pos = new Position(robo.pos.x, robo.pos.y+1);
        		Block b = new Block(astar.G(pos), astar.H(pos), pos, true);
        		map.setMap(b);
        	} else {
        		Position pos = new Position(robo.pos.x, robo.pos.y-1);
        		Block b = new Block(-1, -1, pos, false);
        		map.setMap(b);
        	}
        	
        	
        }
        System.out.println("CHEGOU!!!");
        Button.waitForAnyPress();
    }
    
}

