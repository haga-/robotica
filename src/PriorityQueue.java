import java.util.ArrayList;

public class PriorityQueue {
	ArrayList<Node> nodes;
	
	public PriorityQueue() {
		nodes = new ArrayList<Node>();
	}
	
	public ArrayList<Node> getNodes(){
		return nodes;
	}
	
	public Node first() {
		if(nodes.isEmpty()) return null;
		int min = 0;
		for(int i = 0; i < nodes.size(); ++i) {
			if(nodes.get(i).getF() < nodes.get(min).getF()) min = i;
		}
		Node first = nodes.get(min);
		nodes.remove(min);
		return first;
	}
	
	public void add(Node node) {
		nodes.add(node);
	}
}
