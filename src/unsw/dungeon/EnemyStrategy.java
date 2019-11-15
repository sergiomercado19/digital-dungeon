package unsw.dungeon;

public interface EnemyStrategy {

	/**
	 * calculate the move the enemy will make
	 * @param dungeon the dungeon the enemy is within
	 * @param player the player the enemy is following
	 * @param enemy
	 */
	public void moveEnemy(Dungeon dungeon, Player player, Enemy enemy);
	/**
	 * get the type of enemy
	 * @return
	 */
	public EnemyType getType();

}
