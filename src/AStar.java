import java.util.ArrayList;

public class AStar {
	float heuristic;
	
	int final_x;
	int final_y;
	public AStar(int x, int y){
		this.final_x = x;
		this.final_y = y;
	}
	
	public int manhattan(int x, int y){
		return Math.abs(x - final_x) + Math.abs(y - final_y);
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
