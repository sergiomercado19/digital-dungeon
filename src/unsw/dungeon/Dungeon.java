/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.Random;

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

   public Dungeon(int width, int height) {
      this.width = width;
      this.height = height;
      this.entities = new ArrayList<Entity>();
      this.player = null;
      this.state = DungeonState.INITIALIZING;
   }

   public void setGoals(GoalComponent goals) {      
      this.goals = goals;
   }
   
   public void setState(DungeonState state) {
      this.state = state;
   }
   
   public int getEntityQuantity(String type) {
      int count = 0;
      for (Entity e : this.entities) {
         if (e.getClass().getName().equals(type)) count++; 
      }
      return count;
   }
   
   public ArrayList<Entity> getEntityArrayList(String type) {
      ArrayList<Entity> res = new ArrayList<Entity>();
      for (Entity e : this.entities) {
         if (e.getClass().getName().equals(type)) res.add(e); 
      }
      return res;
   }

   public void alertEnemies() {
      for (Entity e : this.entities) {
         if (e instanceof Enemy) {
            ((Enemy) e).setPlayer(this.player);
         }
      }
   }
   
   public int getWidth() {
      return width;
   }

   public int getHeight() {
      return height;
   }

   public Player getPlayer() {
      return player;
   }

   public void setPlayer(Player player) {
      this.player = player;
   }

   public void addEntity(Entity entity) {
      entities.add(entity);
   }
   
   public void removeEntity(Entity entity) {
      entities.remove(entity);
   }
   
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
	   if (this.goals.isComplete()) this.state = DungeonState.WON;
   }
   
   public void attack(Player player, Enemy enemy) {
      if (player.isInvincible() || player.hasSword()) {
         enemy.notifyObserversOfIncrease();
         this.removeEntity(enemy);
      } else {
         this.removeEntity(player);
         this.setState(DungeonState.LOST);
      }
   }
   
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
   
   public ArrayList<Portal> getPortals() {
	   ArrayList<Portal> res = new ArrayList<Portal>();
      for (Entity e : this.entities) {
         if (e.getClass().getName().equals("portal")) res.add((Portal) e); 
      }
      return res;
   }
   
   public ArrayList<Door> getDoors() {
	   ArrayList<Door> res = new ArrayList<Door>();
      for (Entity e : this.entities) {
         if (e.getClass().getName().equals("door")) res.add((Door) e); 
      }
      return res;
   }
   
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
   
   public ArrayList<Enemy> getEnemies() {
      ArrayList<Enemy> res = new ArrayList<Enemy>();
      for (Entity e : this.entities) {
         if (e.getClass().getName().equals("enemy")) res.add((Enemy) e); 
      }
      return res;
   }
   
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
   
   public boolean canMove(int x, int y, MovableEntity me) {
	   ArrayList<Entity> tileEntities = checkTile(x, y);
	   for (Entity e : tileEntities) {
	      // Boulders can't push boulders
		   if (me instanceof Boulder && e instanceof Boulder) return false;
		   // Movable entities can't go in solid entities
		   else if (e.isSolid()) return false;
	   }
	   return true;
   }
   
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
