package unsw.dungeon;

public class Boulder extends Entity implements MovableEntity {
	private Dungeon dungeon;

	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y, false);
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

	@Override
	public void makeMove(Direction d) {
		MoveContext move = null;
    	switch(d) {
    	case UP:
    		move = new MoveContext(new MoveUp());
			break;
    	case DOWN:
    		move = new MoveContext(new MoveDown());
    		break;
    	case LEFT:
    		move = new MoveContext(new MoveLeft());
    		break;
    	case RIGHT:
    		move = new MoveContext(new MoveRight());
    		break;
    	}
    	move.doMove(this);
	}

}
