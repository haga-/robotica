public class AStar {
	Position initial_pos;
	Position final_pos;
	
	public AStar(Position _initial_pos, Position _final_pos) {
		initial_pos = new Position(_initial_pos.x, _initial_pos.y);
		final_pos = new Position(_final_pos.x, _final_pos.y);
	}
	
	//H
	public int H(Position pos) {
		return Math.abs(pos.x - final_pos.x) + Math.abs(pos.y - final_pos.y) ;
	}
	
	// G
	public int G(Position pos) {
		return Math.abs(pos.x - initial_pos.x) + Math.abs(pos.x - initial_pos.y);
	}	
}
