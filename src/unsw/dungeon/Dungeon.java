/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

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
   
   public void registerMove(int x, int y, Direction d, Player player) {
	   ArrayList<Entity> tileEntities = checkTile(x, y);
	   // FIXME horrible code
	   for(Entity e : tileEntities) {
		   if (e instanceof Collectable) {
			   ((Collectable) e).collect(player);
		   } else if (e instanceof Boulder) {
			   ((Boulder) e).push(d);
		   } else if (e instanceof Exit) {
			   ((Exit) e).trigger();
		   } else if (e instanceof Portal) {
			   ((Portal) e).teleport(player);
		   }
	   }
   }
   
   public void linkPortals(Portal p1, Portal p2) {
	   p1.linkTo(p2);
	   p2.linkTo(p1);
   }
   
   public boolean canMove(int x, int y) {
	   boolean canMove = true;
	   ArrayList<Entity> tileEntities = checkTile(x, y);
	   for(Entity e : tileEntities) {
		   if(e.getSolid()) {
			   canMove = false;
		   }
	   }
	   return canMove;
   }
   
   public ArrayList<Entity> checkTile(int x, int y) {
	   ArrayList<Entity> tileEntities = new ArrayList<>();
	   for(Entity e : entities) {
		   if(e.getX() == x && e.getY() == y) {
			   tileEntities.add(e);
		   }
	   }
	   return tileEntities;
   }
}
