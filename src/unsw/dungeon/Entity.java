package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
	private BooleanProperty inDungeon;
	protected boolean isSolid;

	/**
	 * create a new entity
	 * @param x x position of the entity
	 * @param y y position of the entity
	 */
	public Entity(int x, int y, boolean isSolid) {
		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
		this.inDungeon = new SimpleBooleanProperty(true);
		this.isSolid = isSolid;
	}

	/**
	 * observable x position of the entity
	 * @return
	 */
	public IntegerProperty x() {
		return x;
	}

	/**
	 * observable y position of the entity
	 * @return
	 */
	public IntegerProperty y() {
		return y;
	}

	/**
	 * observable state of whether the entity is in the dungeon
	 * @return
	 */
	public BooleanProperty inDungeon() {
		return this.inDungeon;
	}

	/**
	 * get the y position of the entity
	 * @return
	 */
	public int getY() {
		return y().get();
	}

	/**
	 * get the x position of the entity
	 * @return
	 */
	public int getX() {
		return x().get();
	}

	/**
	 * remove the entity from the dungeon
	 */
	public void removeFromDungeon() {
		this.inDungeon.set(false);
	}

	/**
	 * check if the entity can be passed through
	 * @return
	 */
	public boolean isSolid() {
		return isSolid;
	}

	/**
	 * set whether the entity can be passed through
	 * @param isSolid
	 */
	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	// These 3 can be overridden
	/**
	 * check if an enemy can collide with the entity
	 * @param e
	 * @param d the direction to collide in
	 * @return whether the collision is allowed
	 */
	public boolean canCollide(Enemy e, Direction d) {
		return !isSolid;
	}

	/**
	 * check if the player can collide with the entity
	 * @param p
	 * @param d the direction to collide in
	 * @return whether the collision is allowed
	 */
	public boolean canCollide(Player p, Direction d) {
		return !isSolid;
	}

	/**
	 * check if a boulder can collide with the entity 
	 * @param b
	 * @param d the direction to collide in
	 * @return whether the collision is allowed
	 */
	public boolean canCollide(Boulder b, Direction d) {
		return !isSolid;
	}

	// these 3 can also be overridden
	/**
	 * enemy collides with entity
	 * @param e
	 * @param d the direction of collision
	 */
	public void collide(Enemy e, Direction d) {
		// do nothing
	}

	/**
	 * player collides with the entity
	 * @param p
	 * @param d the direction of collision
	 */
	public void collide(Player p, Direction d) {
		// do nothing
	}

	/**
	 * boulder collides with the entity
	 * @param b
	 * @param d the direction of collision
	 */
	public void collide(Boulder b, Direction d) {
		// do nothing
	}
}
