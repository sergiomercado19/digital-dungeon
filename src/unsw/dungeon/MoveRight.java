package unsw.dungeon;

public class MoveRight implements Move {
	@Override
	public void doMove(Entity e) {
		// check
		e.x().set(e.getX() + 1);
	}
}
