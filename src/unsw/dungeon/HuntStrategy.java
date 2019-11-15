package unsw.dungeon;

/**
 * the strategy an intelligent enemy (elf) will employ
 * to tactically hunt the player down
 * (part of strategy pattern)
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class HuntStrategy implements EnemyStrategy {

	@Override
	public EnemyType getType() {
		return EnemyType.ENEMY;
	}

	@Override
	public void moveEnemy(Dungeon dungeon, Player player, Enemy enemy) {

		if (player != null) {         
			Direction d = null;

			int xDist = Math.abs(enemy.getX()-player.getX());
			int yDist = Math.abs(enemy.getY()-player.getY());

			if (xDist > yDist) {

				// Try moving in the X axis
				if (player.getX() < enemy.getX()) d = Direction.LEFT;
				else d = Direction.RIGHT;

				if (enemy.makeMove(getDirection(player, enemy, d))) return;

				// Try moving in the Y axis
				if (player.getY() < enemy.getY()) d = Direction.UP;
				else d = Direction.DOWN;

				if (enemy.makeMove(getDirection(player, enemy, d))) return;

			} else {

				// Try moving in the Y axis
				if (player.getY() < enemy.getY()) d = Direction.UP;
				else d = Direction.DOWN;

				if (enemy.makeMove(getDirection(player, enemy, d))) return;

				// Try moving in the X axis
				if (player.getX() < enemy.getX()) d = Direction.LEFT;
				else d = Direction.RIGHT;

				if (enemy.makeMove(getDirection(player, enemy, d))) return;

			}

			// if all else fails...
			d = Direction.LEFT;
			if (enemy.makeMove(getDirection(player, enemy, d))) return;
			d = Direction.RIGHT;
			if (enemy.makeMove(getDirection(player, enemy, d))) return;
			d = Direction.UP;
			if (enemy.makeMove(getDirection(player, enemy, d))) return;
			d = Direction.DOWN;
			if (enemy.makeMove(getDirection(player, enemy, d))) return;

		}
	}

	// If player is invincible move in the opposite direction
	private Direction getDirection(Player p, Enemy e, Direction d) {
		if (p.isInvincible().get()) {
			return e.changeDirection(d);
		}

		return d;
	}
}
