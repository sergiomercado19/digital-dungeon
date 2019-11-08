package unsw.dungeon;

import java.util.ArrayList;

/**
 * a boulder, which can be pushed by a player to trigger a floor switch
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Boulder extends Entity implements Movable {

	private Dungeon dungeon;
	private FloorSwitch floorSwitch;

	/**
	 * create a new boulder
	 * @param dungeon the dungeon of the boulder
	 * @param x x position of boulder
	 * @param y y position of boulder
	 */
	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y, false);
		this.dungeon = dungeon;
		this.floorSwitch = null;
	}

	/**
	 * push the boulder in a direction
	 * @param d the direction to push
	 */
//	public void push(Direction d) {
//		makeMove(d);
//	}

	/**
	 * get the dungeon the boulder is contained in
	 * @return the dungeon
	 */
//	@Override
//	public Dungeon getDungeon() {
//		return dungeon;
//	}

	/**
	 * trigger a floor switch when the boulder is on top
	 * @param s
	 */
	public void activateSwitch(FloorSwitch s) {
		this.floorSwitch = s;
		this.floorSwitch.activate();
	}

	/**
	 * untrigger a floor switch when a boulder is moved off it
	 */
	public void deactivateSwitch() {
		if (this.floorSwitch != null) {         
			this.floorSwitch.deactivate();
			this.floorSwitch = null;
		}
	}

	/**
	 * when the boulder is moved, the floor switch is also untriggered
	 */
//	@Override
//	public void makeMove(Direction d) {
		// do nothing?
//	}
//		int x, y;
//		x = getX();
//		y = getY();
//
//		switch (d) {
//		case UP:
//			y = y - 1;
//			break;
//		case DOWN:
//			y = y + 1;
//			break;
//		case LEFT:
//			x = x - 1;
//			break;
//		case RIGHT:
//			x = x + 1;
//			break;
//		}
//
//		ArrayList<Entity> tileEntities = dungeon.checkTile(x, y);
//		boolean canMove = true;
//		for(Entity e : tileEntities) {
//			if(!e.canCollide(this, d)) canMove = false;
//		}
//		if(canMove) {
//			setPosition(x, y);
//
//			// Deactivate floorSwitch before moving
//			deactivateSwitch();
//
//			for(Entity e : tileEntities) {
//				e.collide(this, d);
//			}
//		}
//	}
	
	@Override
	public boolean canCollide(Player p, Direction d) {
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
		return canMove;
	}
	
	@Override
	public void collide(Player p, Direction d) {
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
		// already been checked so should be safe to move?
		setPosition(x, y);
		// Deactivate floorSwitch before moving
		deactivateSwitch();
	}
	
	@Override
	public boolean canCollide(Boulder b, Direction d) {
		return false;
	}
	
	@Override
	public boolean canCollide(Enemy e, Direction d) {
		// for now, enemies cannot collide
		return false;
	}

	@Override
	public void setPosition(int x, int y) {
		x().set(x);
		y().set(y);
	}

}
