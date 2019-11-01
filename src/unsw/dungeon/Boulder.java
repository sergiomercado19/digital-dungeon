package unsw.dungeon;

public class Boulder extends MovableEntity {
	
   private Dungeon dungeon;

	public Boulder(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y, false);
		this.dungeon = dungeon;
	}
	
	public void push(Direction d) {
		// FIXME
		// check
		// then
		makeMove(d);
	}

	public Dungeon getDungeon() {
		return dungeon;
	}

}
