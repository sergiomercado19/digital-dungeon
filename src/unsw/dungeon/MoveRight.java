package unsw.dungeon;

public class MoveRight implements Move {
	@Override
	public void doMove(MovableEntity e) {
		int x = e.getX();
		int y = e.getY();
		
		if(e.getDungeon().canMove(x + 1, y)) {
			e.x().set(x + 1);
		}
	}
}
