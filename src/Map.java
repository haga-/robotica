import java.util.ArrayList;
public class Map{
	Block[][] map = new Block[5][5];
	Position ini, fin;

	public Map(Position i, Position f){
		ini = new Position(i.x, i.y);
		fin = new Position(f.x, f.y);
		for(int y = 1; y < 6; y++) {
			for(int x = 1; x < 6; x++) {
				map[y][x] = new Block(0,0, new Position(x,y), 0);
			}
		}
	}

	public void setMap(Block blk){
		map[blk.getPos().getX()][blk.getPos().getY()] = blk; 
	}

	public Block getBlock(Position pos){
		return map[pos.getX()][pos.getY()];
	}
	// 
	public Boolean isOnMap(Position rob_pos, int orientation) {
		Boolean b = true;

		if (( ((orientation) % 4) + 4) % 4 == 0) {
			if (b && rob_pos.y + 1 < 6) 
				b = map[rob_pos.x][rob_pos.y+1].reachable == 0;
		}
		if (( ((orientation) % 4) + 4) % 4 == 1) {
			if (b && rob_pos.x+1 < 6) 
				b = map[rob_pos.x+1][rob_pos.y].reachable == 0;
		}
		if (( ((orientation) % 4) + 4) % 4 == 2) {
			if (b && rob_pos.y-1 > 0) 
				b = map[rob_pos.x][rob_pos.y-1].reachable == 0;
		}
		if (( ((orientation) % 4) + 4) % 4 == 3) {
			if (b && rob_pos.x-1 >0) 
				b = map[rob_pos.x-1][rob_pos.y].reachable == 0;
		}
		
		
		return b;
	}
	
}
