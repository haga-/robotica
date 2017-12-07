import java.*;
import java.util.ArrayList;

public class Node implements Comparable<Node>{
	Position pos;
	int heuristic;
	int numberSteps;
	Boolean choice;
	private ArrayList<Node> path;
	String direction; 
	
	public Node(Position position, String dir){
		this.pos = position;
		this.direction = dir;
		choice = false;
	}
	
	public void setChoice(Boolean c) {
		this.choice = c;
	}
	
	public Boolean getChoice() {
		return this.choice;
	}
	
	public void setCost(int heuristic){
		this.heuristic = heuristic;
	}
	
	public int getCost(int heuristic){
		return this.heuristic;
	}
	
	public void setPath(ArrayList<Node> path) {
		this.path = path;
	}
	
	public ArrayList<Node> getPath() {
		return this.path;
	}
	
	public void setNumberSteps(int n) {
		this.numberSteps = n;
	}
	
	public int getNumberSteps(int n) {
		return this.numberSteps;
	}
	
	public String getDirection() {
		return this.direction;
	}
	
	public void setDirection(String dir) {
		 this.direction = dir;
	}
	
	public int getCost() {
		return this.numberSteps + this.heuristic;
	}
	
	public Position getPosition() {
		return this.pos;
	}
	
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		if (this.getCost() > o.getCost()) {
			return 1;
		}
		else if (this.getCost() < o.getCost()) {
			return -1;
		}
		
		return 0;
	}
}

