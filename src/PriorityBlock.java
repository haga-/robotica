import java.util.ArrayList;

public class PriorityBlock{
	Block[] line = new Block[20];
	int last;
	public PriorityBlock(Block blk){
		last = 0;
		line[last] = blk;
		last++;
	}

	public Block getFirst(){
		Block aux, aux2;
		for(int i=(last-1);i>=0;i--){
			
			aux2 = line[i];
			
		}
	}

	public void setFirst(Block blk){
		Block aux = blk;
		Block aux2;
		for(int i=0;i<last;i++){
			aux2 = line[i];
			line[i] = aux;
			aux = aux2;
		}
		last++;
	}

}
