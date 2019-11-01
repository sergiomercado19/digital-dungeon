package unsw.dungeon;

public class MoveDown implements Move {
	@Override
	public void doMove(MovableEntity e) {
		int x = e.getX();
		int y = e.getY();
		
		if(e.getDungeon().canMove(x, y + 1)) {
			e.y().set(y + 1);
		}
	}
}
