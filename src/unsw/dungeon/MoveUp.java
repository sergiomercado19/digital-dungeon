package unsw.dungeon;

public class MoveUp implements Move {
	@Override
	public void doMove(Entity e) {
		// check
        e.y().set(e.getY() - 1);
	}
}
