public class Block {
	int g, h;
	Position pos;
	Boolean reachable;
	
	public Block(int g, int h, Position pos, Boolean reachable){
		this.pos = pos;
		this.g = g;
		this.h = h;
		this.reachable = reachable;
	}

	public int getF(){
		return g + h;
	}

	public Boolean getReachable(){
		return reachable;
	}

	public Position getPos(){
		return pos;
	}
}
