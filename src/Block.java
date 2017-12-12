import java.*;

public class Block implement Comparable<Block>{
	int g, h;
	Position pos;
	Boolean old;
	
	public Block(g, h, pos){
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
