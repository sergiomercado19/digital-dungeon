package unsw.dungeon;

import java.util.ArrayList;

/**
 * the strategy a diligent enemy (guard gnome) will employ
 * patrol back and forth along a single axis
 * (part of the strategy pattern)
 * @author Rory
 *
 */
public class PatrolStrategy implements EnemyStrategy {

	private boolean directionIsDefined;
	private Direction d;

	public PatrolStrategy() {
		super();
		this.directionIsDefined = false;
	}

	@Override
	public EnemyType getType() {
		return EnemyType.GUARD;
	}

	@Override
	public void moveEnemy(Dungeon dungeon, Player player, Enemy enemy) {

		if (!directionIsDefined) defineDirection(dungeon, enemy);

		if (enemy.makeMove(this.d)) {
			return;
		} else {
			// Change direction
			switch (this.d) {
			case UP:
				this.d = Direction.DOWN;
				break;
			case DOWN:
				this.d = Direction.UP;
				break;
			case LEFT:
				this.d =  Direction.RIGHT;
				break;
			case RIGHT:
				this.d = Direction.LEFT;
				break;
			}
		}

	}

	/**
	 * define the axis to patrol
	 * @param d
	 * @param e
	 */
	private void defineDirection(Dungeon d, Enemy e) {

		// Vertical patrol
		if (canPatrol(d, e.getX(), e.getY()-1) && canPatrol(d, e.getX(), e.getY()+1)) {
			this.d = Direction.UP;
		}
		// Horizontal patrol
		else {
			this.d = Direction.LEFT;
		}

		this.directionIsDefined = true;
	}

	/** 
	 * check if the enemy can patrol an axis
	 * @param d
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean canPatrol(Dungeon d, int x, int y) {
		ArrayList<Entity> tileEntities = d.checkTile(x, y);
		for (Entity e : tileEntities) {
			if (!e.isSolid()) {
				return false;
			}
		}
		return true;
	}
}
