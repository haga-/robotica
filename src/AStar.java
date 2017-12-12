import java.util.ArrayList;

public class AStar {
	float heuristic;
	
	int final_x;
	int final_y;
	Position initial_pos;
	Position final_pos;
	public AStar(int x, int y){
		this.final_x = x;
		this.final_y = y;
	}
	
	public AStar(Position _initial_pos, Position _final_pos) {
		initial_pos = new Position(_initial_pos.x, _initial_pos.y);
		final_pos = new Position(_final_pos.x, _final_pos.y);
	}
	
	public int manhattan(int x, int y){
		return Math.abs(x - final_x) + Math.abs(y - final_y);
	}
	
	//H
	public int H(Position pos) {
		return Math.abs(pos.x - final_pos.x) + Math.abs(pos.y - final_pos.y) ;
	}
	
	// G
	public int G(Position pos) {
		return Math.abs(pos.x - initial_pos.x) + Math.abs(pos.x - initial_pos.y);
	}
	
	public int calcNumberSteps(int x, int y) {
		int caminho = 0;
		
			if (x > final_x) {
				caminho += x - final_x; 
			} else {
				caminho += final_x - x;
			}
			if (y > final_y) {
				caminho += y - final_y; 
			} else {
				caminho += final_y - y;
			}
		
		return caminho;
	}
	
}
