package unsw.dungeon;

public class MoveLeft implements Move {
	@Override
	public void doMove(Entity e) {
		// check
		e.x().set(e.getX() - 1);
	}
}
