import java.util.ArrayList;
public class Map{
	Block[][] map = new Block[7][7];
	Position ini, fin;

	public Map(Position i, Position f){
		ini = new Position(i.x, i.y);
		fin = new Position(f.x, f.y);
		setMap(new Block(0, 0, ini, false));
		setMap(new Block(0, 0, fin, true));
	}

	public void setMap(Block blk){
		map[blk.getPos().getX()][blk.getPos().getY()] = blk; 
	}

	public Block getBlock(Position pos){
		return map[pos.getX()][pos.getY()];
	}
	// 
	public ArrayList<Boolean> isOnMap(Position rob_pos) {
		ArrayList<Boolean> b_array = new ArrayList<Boolean>(4);
		b_array.set(0, false);
		b_array.set(1, false);
		b_array.set(2, false);
		b_array.set(3, false);
		
		if (rob_pos.x - 1 > 0)
			b_array.set(0, map[rob_pos.x-1][rob_pos.y] != null);
		if(rob_pos.x + 1 < 7)
			b_array.set(1, map[rob_pos.x+1][rob_pos.y] != null);
		if (rob_pos.y - 1 > 0)
			b_array.set(2, map[rob_pos.x][rob_pos.y-1]	 != null);
		if (rob_pos.y + 1 < 7)
			b_array.set(3, map[rob_pos.x+1][rob_pos.y+1] != null);
		
		return b_array;
	}
	
}
