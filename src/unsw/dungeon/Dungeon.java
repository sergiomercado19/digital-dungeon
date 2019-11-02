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

   public Dungeon(int width, int height) {
      this.width = width;
      this.height = height;
      this.entities = new ArrayList<Entity>();
      this.player = null;
   }

   public void setGoals(GoalComponent goals) {      
      this.goals = goals;
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
   
   public void registerMove(int x, int y, Direction d, MovableEntity me) {
      
	   ArrayList<Entity> tileEntities = checkTile(x, y);
	   // FIXME horrible code
	   for(Entity e : tileEntities) {
		   // these should only be triggered by players
		   if (e instanceof Collectable) {
			   ((Collectable) e).collect(player);
		   } else if (e instanceof Boulder) {
			   ((Boulder) e).push(d);
		   } else if (e instanceof Exit) {
			   ((Exit) e).trigger();
		   } else if (e instanceof Portal) {
			   ((Portal) e).teleport(player);
			   
		   // this will be triggered by a boulder being pushed onto a floor switch
		   } else if (e instanceof FloorSwitch) {
			   ((FloorSwitch) e).trigger();
		   }
	   }
   }
   
   public void linkPortals() {
      // Portals are linked randomly in pairs
      ArrayList<Entity> entities = this.getEntityArrayList("portal");
      Random r = new Random();
      while (entities.size() > 1) {
         int r1 = r.nextInt(entities.size());
         int r2 = r.nextInt(entities.size());
         Portal p1 = (Portal) entities.remove(r1);
         Portal p2 = (Portal) entities.remove(r2);
         p1.linkTo(p2);
         p2.linkTo(p1);
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
