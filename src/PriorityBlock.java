import java.util.ArrayList;

public class PriorityBlock {
	ArrayList<Block> blocks;
	
	public PriorityBlock() {
		blocks = new ArrayList<Block>();
	}
	
	public ArrayList<Block> getBlocks(){
		return blocks;
	}
	
	public Block first() {
		if(blocks.isEmpty()) return null;
		int min = 0;
		for(int i = 0; i < blocks.size(); ++i) {
			if(blocks.get(i).getF() < blocks.get(min).getF()) min = i;
		}
		Block first = blocks.get(min);
		blocks.remove(min);
		return first;
	}
	
	public void add(Block block) {
		if(!blocks.contains(block))
			blocks.add(block);
	}
	
	public Boolean isEmpty() {
		return blocks.isEmpty();
	}
}
