
public class Position {
	int x, y;
	
	public Position(int posx, int posy){
		x = posx;
		y = posy;		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		final Position other = (Position) obj;
		if(this.x == other.x && this.y == other.y) return true;
		return false;
	}
}
