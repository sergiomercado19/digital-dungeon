package unsw.dungeon;

import java.util.ArrayList;

/**
 * an enemy entity, seeks out the player to kill
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Enemy extends Entity implements Movable, GoalSubject {

	private Dungeon dungeon;
	private Player player;
	private ArrayList<GoalObserver> goalObservers;

	/**
	 * create a new enemy
	 * @param dungeon the dungeon the enemy is contained within
	 * @param x x position of the enemy
	 * @param y y position of the enemy
	 */
	public Enemy(Dungeon dungeon, int x, int y) {
		super(x, y, false);
		this.dungeon = dungeon;
		this.goalObservers = new ArrayList<GoalObserver>();
		this.player = null;
	}

	/**
	 * set the target player of the enemy
	 * @param player the player to target
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * pathfind one step towards the current position of the player
	 */
	public void moveTowardsPlayer() {

		if (this.player != null) {         
			Direction d = null;

			int xDist = Math.abs(this.getX()-this.player.getX());
			int yDist = Math.abs(this.getY()-this.player.getY());

			if (xDist > yDist) {
				if (this.player.getX() < this.getX()) d = Direction.LEFT;
				else d = Direction.RIGHT;
			} else {
				if (this.player.getY() < this.getY()) d = Direction.UP;
				else d = Direction.DOWN;
			}

			// If player is invincible move in the opposite direction
			if (this.player.isInvincible()) {
				switch (d) {
				case UP:
					d = Direction.DOWN;
					break;
				case DOWN:
					d = Direction.UP;
					break;
				case LEFT:
					d = Direction.RIGHT;
					break;
				case RIGHT:
					d = Direction.LEFT;
					break;
				}
			}

			makeMove(d);
		}
	}

	/**
	 * kill off the enemy
	 */
	public void die() {
		notifyObserversOfIncrease();
	}

	@Override
	public void addObserver(GoalObserver o) {
		this.goalObservers.add(o);
	}

	@Override
	public void removeObserver(GoalObserver o) {
		this.goalObservers.remove(o);
	}

	@Override
	public void notifyObserversOfIncrease() {
		for (GoalObserver go : this.goalObservers) {
			go.increaseProgress();
		}  
	}

	@Override
	public void notifyObserversOfDecrease() {
		for (GoalObserver go : this.goalObservers) {
			go.decreaseProgress();
		}  
	}

	@Override
	public void setPosition(int x, int y) {
		x().set(x);
		y().set(y);
	}

	public void makeMove(Direction d) {
		int x, y;
		x = getX();
		y = getY();
		
		switch (d) {
		case UP:
			y = y - 1;
			break;
		case DOWN:
			y = y + 1;
			break;
		case LEFT:
			x = x - 1;
			break;
		case RIGHT:
			x = x + 1;
			break;
		}

		ArrayList<Entity> tileEntities = dungeon.checkTile(x, y);
		boolean canMove = true;
		for(Entity e : tileEntities) {
			if(!e.canCollide(this, d)) canMove = false;
		}
		if(canMove) {
			setPosition(x, y);
			for(Entity e : tileEntities) {
				e.collide(this, d);
			}
		}
	}
	
	@Override
	public boolean canCollide(Enemy e, Direction d) {
		return false;
	}
	
	@Override
	public boolean canCollide(Boulder b, Direction d) {
		return false;
	}
	
	@Override
	public void collide(Player p, Direction d) {
		// work out death stuff
		System.out.println("enemy registered");
		dungeon.fight(p, this);
	}
}
