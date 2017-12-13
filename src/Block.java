public class Block {
	int g, h;
	Position pos;
	int reachable; // 0 não visto, 1 avistado, 2 visitado, 3 obstáculo
	
	public Block(int g, int h, Position pos, int reachable){
		this.pos = pos;
		this.g = g;
		this.h = h;
		this.reachable = reachable;
	}

	public int getF(){
		return g + h;
	}

	public Position getPos(){
		return pos;
	}
	
}
