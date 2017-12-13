public class Block {
	int g, h;
	Position pos;
	Boolean old;
	
	public Block(int g, int h, Position pos){
		this.pos = pos;
		this.g = g;
		this.h = h;
		this.old = false;
	}

	public int getF(){
		return g + h;
	}

	public Boolean getOld(){
		return old;
	}

	public Position getPos(){
		return pos;
	}

	public void setOld(){
		old = true;
	}
}
