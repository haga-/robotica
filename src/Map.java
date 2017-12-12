import java.*;
import java.util.ArrayList;

public class Map{
	Block[][] map = new Block[7][7];
	Position ini, fin;

	public Map(Position i, Position f){
		ini = new Position(i);
		fin = new Position(f);
		setMap(new Block(0, 0, ini));
		setMap(new Block(0, 0, fin));
	}

	public void setMap(Block blk){
		map[blk.getPos().getX()][blk.getPos().getY()] = blk; 
	}

	public Block getBlock(Position pos){
		return Block[pos.getX()][pos.getY];
	}
}
