import lejos.nxt.Button;
import lejos.nxt.addon.RealTimeClock;

public class HelloWorld {
	static Position initial_pos = new Position(3,1);
	static Position final_pos = new Position(3,5);
	static Map map = new Map(initial_pos, final_pos);
	static AStar astar = new AStar(initial_pos, final_pos);
	static PriorityBlock pb = new PriorityBlock();
	
	public static Boolean reachable(Position pos, int direction) {
    	int i = 0;
    	Boolean b = true;
    	direction--;
		while (i < 3) {
			switch( ((direction % 4) + 4) % 4) {
				case 0:
					if(pos.y+1 < 6) b = false;
					break;
				case 1:
					if (pos.x+1 < 6) b = false;
					break;
				case 2: 
					if (pos.y-1 > 0) b = false;
					break;
				case 3: 
					if (pos.x-1 >0) b = false;
					break;
			}
			direction++;
			i++;
		}
			
		return b;
	}	
	
	public static void main(String[] args) {
        Robo robo = new Robo(initial_pos.x, initial_pos.y);
        Position pos;
        Block blk;
        while(!robo.pos.equals(final_pos)) {
        	Boolean onMap = map.isOnMap(robo.pos, robo.direction);
        	Boolean look = false;
        	if (onMap) {
        		// Direita
        		if(reachable(robo.pos, robo.direction)) {
        			pos = new Position(robo.pos.x+1, robo.pos.y);
            		blk = map.getBlock(pos);
            		blk.g = astar.G(pos); blk.h = astar.H(pos);
            		look = robo.lookRight();
            		if(blk.reachable != 2)
            			blk.reachable = look ? 1:3;
            		map.setMap(blk);
            		if (blk.reachable == 1) pb.add(blk);
        		}
        		
        		// Esquerda
        		if(reachable(robo.pos, robo.direction)) {
	        		pos = new Position(robo.pos.x-1, robo.pos.y);
	        		blk = map.getBlock(pos);
	        		blk.g = astar.G(pos); blk.h = astar.H(pos);
	        		look = robo.lookLeft();
	        		if(blk.reachable != 2)
	        			blk.reachable = look ? 1:3;
	        		map.setMap(blk);
	        		if (blk.reachable == 1) pb.add(blk);
        		}
        		
        		// Frente
        		if(reachable(robo.pos, robo.direction)) {
	        		System.out.println("NO Front");
	        		pos = new Position(robo.pos.x, robo.pos.y+1);
	        		blk = map.getBlock(pos);
	        		blk.g = astar.G(pos); blk.h = astar.H(pos);
	        		look = robo.lookFront();
	        		if(blk.reachable != 2)
	        			blk.reachable = look ? 1:3;
	        		map.setMap(blk);
	        		if (blk.reachable == 1) pb.add(blk);
        		}
        	}
        	blk = pb.first();
        	robo.move(blk);        	
        }
        System.out.println("CHEGOU!!!");
        Button.waitForAnyPress();
    }
}