package unsw.dungeon;

public class Boulder extends Entity implements MovableEntity {
	
	private Dungeon dungeon;

	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y, false);
		this.dungeon = dungeon;
		// TODO Auto-generated constructor stub
	}
	
	public void push(Direction direction) {
		
	}

	public Dungeon getDungeon() {
		return dungeon;
	}

	@Override
	public void makeMove(Direction d) {
		// TODO Auto-generated method stub
		
	}

}
