/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Dungeon {

	private int width, height;
	private ArrayList<Entity> entities;
	private Player player;
	private GoalComponent goal;
	private DungeonState state;
	private BooleanProperty isGameOver;

	/**
	 * create a new dungeon
	 * @param width the width of the dungeon
	 * @param height the height of the dungeon
	 */
	public Dungeon(int width, int height) {
		this.width = width;
		this.height = height;
		this.entities = new ArrayList<Entity>();
		this.player = null;
		this.goal = null;
		this.state = DungeonState.INITIALIZING;
		this.isGameOver = new SimpleBooleanProperty(false);
	}

	/**
	 * set up the dungeon
	 * @param goals the goals to add
	 */
	public void initialize(GoalComponent goals) {
		// 1. Alert enemies of players
		this.alertEnemies();

		// 2. Link portals
		this.linkPortals();

		// 3. Link keys to doors
		this.linkKeysToDoors();

		// 4. Set dungeon goals
		this.setGoals(goals);

		// 5. Trigger floorSwitches that start with boulders on them
		this.checkFloorSwitchState();

	}

	/**
	 * check if any floor switches start with boulders on top
	 * and trigger them accordingly
	 */
	private void checkFloorSwitchState() {
		ArrayList<FloorSwitch> floorSwitches = getFloorSwitches();
		for (FloorSwitch fs : floorSwitches) {
			ArrayList<Entity> tileEntities = checkTile(fs.getX(), fs.getY());
			for (Entity e : tileEntities) {
				if (e instanceof Boulder) fs.activate();
			}
		}

	}

	/**
	 * check if the game is over, an observable property
	 * @return
	 */
	public BooleanProperty isGameOver() {
		return this.isGameOver;
	}

	/**
	 * set the goals the player must achieve within the dungeon
	 * @param goals
	 */
	public void setGoals(GoalComponent goals) {
		this.goal = goals;
	}

	/**
	 * get goal from the dungeon
	 * @return
	 */
	public GoalComponent getGoal() {
		return this.goal;
	}

	/**
	 * set the current state of the dungeon
	 * @param state
	 */
	public void setState(DungeonState state) {
		this.state = state;
	}

	/**
	 * get the current state of the dungeon
	 * @return the current state of the dungeon
	 */
	public DungeonState getState() {
		return state;
	}

	/**
	 * get all entities of a certain type within the dungeon
	 * @param type the type of entity
	 * @return the list of all entities of the type
	 */
	public ArrayList<Entity> getEntityArrayList(String type) {
		ArrayList<Entity> res = new ArrayList<Entity>();
		for (Entity e : this.entities) {
			if (e.getClass().getName().toLowerCase().equals("unsw.dungeon." + type)) res.add(e); 
		}
		return res;
	}

	/**
	 * alert enemies of the player so they will begin to pathfind to them
	 */
	public void alertEnemies() {
		ArrayList<Enemy> enemies = getEnemies();
		for (Enemy e : enemies) {
			e.setPlayer(player);
		}
	}

	/**
	 * get the width of the dungeon
	 * @return the width of the dungeon
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * get the height of the dungeon
	 * @return the height of the dungeon
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * get the player within the dungeon
	 * @return the player within the dungeon
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * set the player within the dungeon
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * add an entity to the dungeon
	 * @param entity the entity to add
	 */
	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	/**
	 * remove an entity from the dungeon
	 * @param entity the entity to remove
	 */
	public void removeEntity(Entity entity) {
		// Remove from View
		entity.removeFromDungeon();
		// Remove from Model
		entities.remove(entity);
	}

	public void registerPlayerMove() {
		ArrayList<Enemy> enemies = getEnemies();
		for (Enemy e : enemies) {
			e.moveTowardsPlayer();
		}

		if (this.goal != null && this.goal.isComplete()) {
			this.state = DungeonState.WON;
			this.isGameOver.set(true);
		}
	}

	/**
	 * determines the outcome of a player and enemy coming into contact to see who dies
	 * @param player the player in contact
	 * @param enemy the enemy in contact
	 */
	public void fight(Player player, Enemy enemy) {
		if (player.isInvincible().get()) {
			enemy.die();
			this.removeEntity((Entity) enemy);

			// Play sound
			SoundEffects.playSwordKill();
		} else if (player.hasSword().get()) {
			player.hitEnemy();
			enemy.die();
			this.removeEntity((Entity) enemy);

			// Play sound
			SoundEffects.playSwordKill();
		} else {
			this.removeEntity(player);
			this.player = null;
			this.setState(DungeonState.LOST);
			this.isGameOver.set(true);
		}
	}

	/**
	 * link all portals together with corresponding ids
	 */
	public void linkPortals() {
		ArrayList<Portal> portals = getPortals();
		for(Portal p1 : portals) {
			for(Portal p2 : portals) {
				if(!p1.equals(p2) && p1.getID() == p2.getID()) {
					p1.linkTo(p2);
					p2.linkTo(p1);
				}
			}
		}
	}

	/**
	 * get a list of portals in the dungeon
	 * @return a list of portals
	 */
	public ArrayList<Portal> getPortals() {
		ArrayList<Portal> res = new ArrayList<Portal>();
		for (Entity e : this.entities) {
			if (e instanceof Portal) res.add((Portal) e); 
		}
		return res;
	}

	/**
	 * get a list of doors in the dungeon
	 * @return a list of doors
	 */
	public ArrayList<Door> getDoors() {
		ArrayList<Door> res = new ArrayList<Door>();
		for (Entity e : this.entities) {
			if (e instanceof Door) res.add((Door) e); 
		}
		return res;
	}

	/**
	 * get a list of keys in the dungeon
	 * @return a list of keys
	 */
	public ArrayList<Key> getKeys() {
		ArrayList<Key> res = new ArrayList<Key>();
		for (Entity e : this.entities) {
			if(e instanceof Key) {
				res.add((Key) e);
			}
		}

		return res;
	}

	/**
	 * get a list of enemies in the dungeon
	 * @return a list of enemies
	 */
	public ArrayList<Enemy> getEnemies() {
		ArrayList<Enemy> res = new ArrayList<Enemy>();
		for (Entity e : this.entities) {
			if (e instanceof Enemy) res.add((Enemy) e); 
		}
		return res;
	}

	/**
	 * get a list of floorSwitches in the dungeon
	 * @return a list of floor floorSwitches
	 */
	public ArrayList<FloorSwitch> getFloorSwitches() {
		ArrayList<FloorSwitch> res = new ArrayList<FloorSwitch>();
		for (Entity e : this.entities) {
			if (e instanceof FloorSwitch) res.add((FloorSwitch) e); 
		}
		return res;
	}

	/**
	 * link all keys to doors with corresponding ids
	 */
	public void linkKeysToDoors() {
		ArrayList<Key> keys = getKeys();
		ArrayList<Door> doors = getDoors();
		for(Key k : keys) {
			for(Door d : doors) {
				if(k.getID() == d.getID()) {
					k.linkDoor(d);
				}
			}
		}
	}

	/**
	 * get a list of entities occupying a given tile
	 * @param x the x position of the tile
	 * @param y the y position of the tile
	 * @return a list of entities
	 */
	public ArrayList<Entity> checkTile(int x, int y) {
		ArrayList<Entity> tileEntities = new ArrayList<Entity>();
		for (Entity e : entities) {
			if (e.getX() == x && e.getY() == y) {
				tileEntities.add(e);
			}
		}
		return tileEntities;
	}
}
