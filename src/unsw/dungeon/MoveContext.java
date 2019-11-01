package unsw.dungeon;

public class MoveContext {
	private Move move;
	
	public MoveContext(Move move) {
		this.move = move;
	}
	
	public void doMove(MovableEntity e) {
		move.doMove(e);
	}
}
