package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The player entity
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Player extends Entity implements Movable {

	private Dungeon dungeon;
	private int swordHits;
	private int invincibilityLeft;
	private BooleanProperty hasSword;
	private BooleanProperty isInvincible;

	/**
	 * create a new player
	 * @param dungeon the dungeon the player is contained within
	 * @param x x position of the player
	 * @param y y position of the player
	 */
	public Player(Dungeon dungeon, int x, int y) {
		super(x, y, false);
		this.dungeon = dungeon;
		this.swordHits = 0;
		this.invincibilityLeft = 0;

		this.hasSword = new SimpleBooleanProperty(false);
		this.isInvincible = new SimpleBooleanProperty(false);
	}

	/**
	 * get the number of sword hits the player has before the sword is removed
	 * @return
	 */
	public int getSwordHits() {
		return this.swordHits;
	}

	/**
	 * get the number of tiles left the player can traverse before their invincibility expires
	 * @return
	 */
	public int getInvincibilityLeft() {
		return this.invincibilityLeft;
	}

	/**
	 * give the player a sword, with 5 hits remaining
	 */
	public void pickupSword(Sword s) {
		// only pick up if player doesn't already have a sword
		if (this.swordHits == 0) {
			this.swordHits += 5;
			this.dungeon.removeEntity(s);
			this.hasSword.set(true);
		}
	}

	/**
	 * hit an enemy with a sword, taking one hit point away from the sword
	 */
	public void hitEnemy() {
		if (this.swordHits > 0) {
			this.swordHits--;
			if (swordHits == 0) {
				this.hasSword.set(false);
			}
		}
	}

	/**
	 * observable check if the player is holding a sword
	 * @return
	 */
	public BooleanProperty hasSword() {
		return this.hasSword;
	}

	/**
	 * observable check if the player is invincible
	 * @return
	 */
	public BooleanProperty isInvincible() {
		return this.isInvincible;
	}

	/**
	 * turn the player invincible for a certain length of time (tiles travelled)
	 */
	public void becomeInvincible(Invincibility i) {
		this.invincibilityLeft += 15;
		this.dungeon.removeEntity(i);
		this.isInvincible.set(true);
	}

	/**
	 * add a new key id to the player's inventory
	 * @param ID the id of the key to add
	 */
	public void pickupKey(Key k) {
		this.dungeon.removeEntity(k);
	}

	/**
	 * collect treasure from the dungeon floor
	 * @param t
	 */
	public void pickupTreasure(Treasure t) {
		this.dungeon.removeEntity(t);
	}

	/**
	 * move the player, and also decrease the amount of invincibility they have left
	 */
	public void makeMove(Direction d) {
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
		for(Entity e : tileEntities) {
			if(!e.canCollide(this, d)) canMove = false;
		}
		if(canMove) {
			setPosition(x, y);

			// Decrease invincibility when you move
			if (this.invincibilityLeft > 0) {
				this.invincibilityLeft--;
				if (this.invincibilityLeft == 0) {
					this.isInvincible.set(false);
				}
			}

			for(Entity e : tileEntities) {
				e.collide(this, d);
			}

			// tell the dungeon we moved
			dungeon.registerPlayerMove();
		}
	}


	@Override
	public void collide(Enemy e, Direction d) {
		dungeon.fight(this, e);
	}

	@Override
	public boolean canCollide(Boulder b, Direction d) {
		return false;
	}

	@Override
	public void setPosition(int x, int y) {
		x().set(x);
		y().set(y);
	}
}
