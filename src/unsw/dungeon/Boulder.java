package unsw.dungeon;

public class Boulder extends Entity {

   private Dungeon dungeon;
   
	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y, false);
		this.dungeon = dungeon;
		// TODO Auto-generated constructor stub
	}
	
	public void push(Direction direction) {
		
	}

}
