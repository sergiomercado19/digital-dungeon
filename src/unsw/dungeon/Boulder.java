package unsw.dungeon;

public class Boulder extends MovableEntity {
	
   private Dungeon dungeon;

	public Boulder(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y, false);
		this.dungeon = dungeon;
		// TODO Auto-generated constructor stub
	}
	
	public void push(Direction d) {
		// check
		// then
		makeMove(d);
	}

	public Dungeon getDungeon() {
		return dungeon;
	}

}
