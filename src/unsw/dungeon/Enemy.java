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
	private EnemyStrategy strategy;
	private ArrayList<GoalObserver> goalObservers;

	/**
	 * create a new enemy
	 * @param dungeon the dungeon the enemy is contained within
	 * @param x x position of the enemy
	 * @param y y position of the enemy
	 */
	public Enemy(Dungeon dungeon, int x, int y, EnemyStrategy strategy) {
		super(x, y, false);
		this.dungeon = dungeon;
		this.player = null;
		this.strategy = strategy;
		this.goalObservers = new ArrayList<GoalObserver>();
	}

	/**
	 * set the target player of the enemy
	 * @param player the player to target
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * make move according to set strategy
	 */
	public void moveTowardsPlayer() {
	   this.strategy.moveEnemy(this.dungeon, this.player, this);
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
	
	public EnemyType getEnemyType() {
	   return this.strategy.getType();
	}

	public boolean makeMove(Direction d) {
		int x, y;
		x = getX();
		y = getY();
		int width, height;
	      width = dungeon.getWidth();
	      height = dungeon.getHeight();

	      switch (d) {
	      case UP:
	         y = Math.floorMod(y - 1, height);
	         break;
	      case DOWN:
	    	  y = Math.floorMod(y + 1, height);
	         break;
	      case LEFT:
	         x = Math.floorMod(x - 1, width);
	         break;
	      case RIGHT:
	    	  x = Math.floorMod(x + 1, width);
	         break;
	      }

		ArrayList<Entity> tileEntities = dungeon.checkTile(x, y);
		boolean canMove = true;
		for (Entity e : tileEntities) {
			if(!e.canCollide(this, d)) canMove = false;
		}
		
		if (canMove) {
			setPosition(x, y);
			for(Entity e : tileEntities) {
				e.collide(this, d);
			}
		}
		
		return canMove;
	}
	
	public Direction changeDirection(Direction d) {
      switch (d) {
      case UP:
         return Direction.DOWN;
      case DOWN:
         return Direction.UP;
      case LEFT:
         return  Direction.RIGHT;
      case RIGHT:
         return Direction.LEFT;
      default:
         return Direction.UP;
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
		dungeon.fight(p, this);
	}
}
