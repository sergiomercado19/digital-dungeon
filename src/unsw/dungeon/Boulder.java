package unsw.dungeon;

import java.util.ArrayList;

/**
 * a boulder, which can be pushed by a player to trigger a floor switch
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Boulder extends Entity implements Movable {

	private Dungeon dungeon;

	/**
	 * create a new boulder
	 * @param dungeon the dungeon of the boulder
	 * @param x x position of boulder
	 * @param y y position of boulder
	 */
	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y, false);
		this.dungeon = dungeon;
	}

	@Override
	public boolean canCollide(Player p, Direction d) {
		return canMove(d);
	}
	
	@Override
	public boolean canCollide(Enemy e, Direction d) {
		return canMove(d);
	}
	
	public boolean canMove(Direction d) {
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
		return canMove;
	}

	public void doMove(Direction d) {
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

		setPosition(x, y);

		ArrayList<Entity> tileEntities = dungeon.checkTile(x, y);
		for(Entity e : tileEntities) {
			e.collide(this, d);
		}
	}

	@Override
	public void collide(Player p, Direction d) {
		doMove(d);
	}

	@Override
	public boolean canCollide(Boulder b, Direction d) {
		return false;
	}

	@Override
	public void collide(Enemy e, Direction d) {
		doMove(d);
	}

	@Override
	public void setPosition(int x, int y) {
		x().set(x);
		y().set(y);
	}
}
