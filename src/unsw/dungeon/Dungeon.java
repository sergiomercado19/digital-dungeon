/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

   private int width, height;
   private ArrayList<Entity> entities;
   private Player player;
   private GoalComponent goals;
   private DungeonState state;

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
      this.state = DungeonState.INITIALIZING;
   }

   /**
    * set the goals the player must achieve within the dungeon
    * @param goals
    */
   public void setGoals(GoalComponent goals) {      
      this.goals = goals;
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
    * get the count of a certain entity within the dungeon
    * @param type the type of entity
    * @return the count for of the entity
    */
   public int getEntityQuantity(String type) {
      int count = 0;
      for (Entity e : this.entities) {
         if (e.getClass().getName().equals(type)) count++; 
      }
      return count;
   }
   
   /**
    * get all entities of a certain type within the dungeon
    * @param type the type of entity
    * @return the list of all entities of the type
    */
   public ArrayList<Entity> getEntityArrayList(String type) {
      ArrayList<Entity> res = new ArrayList<Entity>();
      for (Entity e : this.entities) {
         if (e.getClass().getName().equals(type)) res.add(e); 
      }
      return res;
   }

   /**
    * alert enemies of the player so they will begin to pathfind to them
    */
   public void alertEnemies() {
      for (Entity e : this.entities) {
         if (e instanceof Enemy) {
            ((Enemy) e).setPlayer(this.player);
         }
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
      entities.remove(entity);
   }
   
   /**
    * when an entity has moved, update parts of the dungeon
    * @param x the new x position
    * @param y the new y position
    * @param d the direction the entity was going
    * @param me the entity
    */
   public void registerMove(int x, int y, Direction d, MovableEntity me) {
      
      // If the player moves, enemies move as well
      if (me instanceof Player) {
         for (Enemy e : getEnemies()) {
            e.moveTowardsPlayer();
         }
      }
      
	   ArrayList<Entity> tileEntities = checkTile(x, y);
	   // FIXME horrible code
	   for (Entity e : tileEntities) {
		   if (e instanceof Collectable && me instanceof Player) {
		      // Don't pick up Sword if Player already has one
		      if (((Collectable) e).getItem() instanceof Sword && ((Player) me).hasSword())
		         continue;

		      ((Collectable) e).collect(player);
		      removeEntity(e);
		   } else if (e instanceof Player && me instanceof Enemy) {
            attack((Player) e, (Enemy) me);
         } else if (me instanceof Player && e instanceof Enemy) {
            attack((Player) me, (Enemy) e);
	      } else if (e instanceof Boulder) {
			   ((Boulder) e).push(d);
		   } else if (e instanceof Exit) {
			   ((Exit) e).trigger();
		   } else if (e instanceof Portal) {
			   ((Portal) e).teleport(player);
		   } else if (e instanceof FloorSwitch && me instanceof Boulder) {
			   ((Boulder) me).activateSwitch((FloorSwitch) e);
		   }
	   }
	   
	   // Check if game was won
	   if (this.goals == null || this.goals.isComplete()) this.state = DungeonState.WON;
   }
   
   /**
    * determines the outcome of a player and enemy coming into contact to see who dies
    * @param player the player in contact
    * @param enemy the enemy in contact
    */
   public void attack(Player player, Enemy enemy) {
      if (player.isInvincible() || player.hasSword()) {
         enemy.die();
         this.removeEntity(enemy);
      } else {
         this.removeEntity(player);
         this.setState(DungeonState.LOST);
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
         if (e.getClass().getName().equals("portal")) res.add((Portal) e); 
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
         if (e.getClass().getName().equals("door")) res.add((Door) e); 
      }
      return res;
   }
   
   /**
    * get a list of keys in the dungeon
    * @return a list of keys
    */
   public ArrayList<Key> getKeys() {
	   ArrayList<Key> res = new ArrayList<Key>();
	   for (Entity e : getEntityArrayList("collectable")) {
		   Collectable c = (Collectable) e;
		   Item i = c.getItem();
		   if(c.getItem().getClass().getName().equals("key")) {
			   res.add((Key) i);
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
         if (e.getClass().getName().equals("enemy")) res.add((Enemy) e); 
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
    * check if an entity can move to a given location
    * @param x the new x position
    * @param y the new y position
    * @param me the entity
    * @return whether the entity can move or not
    */
   public boolean canMove(int x, int y, MovableEntity me) {
	   // FIXME can't go outside the border
	   ArrayList<Entity> tileEntities = checkTile(x, y);
	   for (Entity e : tileEntities) {
	      // Boulders can't push boulders
		   if (me instanceof Boulder && e instanceof Boulder) return false;
		   // Movable entities can't go in solid entities
		   else if (e.isSolid()) return false;
	   }
	   return true;
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
