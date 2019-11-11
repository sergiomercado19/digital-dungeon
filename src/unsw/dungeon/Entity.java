package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Entity {

	// IntegerProperty is used so that changes to the entities position can be
	// externally observed.
	private IntegerProperty x, y;
	protected boolean isSolid;

	/**
	 * create a new entity
	 * @param x x position of the entity
	 * @param y y position of the entity
	 */
	public Entity(int x, int y, boolean isSolid) {
		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
		this.isSolid = isSolid;
	}

	public IntegerProperty x() {
		return x;
	}

	public IntegerProperty y() {
		return y;
	}

	public int getY() {
		return y().get();
	}

	public int getX() {
		return x().get();
	}

	public boolean isSolid() {
		return isSolid;
	}

	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	// These 3 can be overridden
	public boolean canCollide(Enemy e, Direction d) {
		return !isSolid;
	}

	public boolean canCollide(Player p, Direction d) {
		return !isSolid;
	}

	public boolean canCollide(Boulder b, Direction d) {
		return !isSolid;
	}
	
	// these 3 must be overridden
	public void collide(Enemy e, Direction d) {
		// do nothing
	}

	public void collide(Player p, Direction d) {
		// do nothing
	}

	public void collide(Boulder b, Direction d) {
		// do nothing
	}
}
