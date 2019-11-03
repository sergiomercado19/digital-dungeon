package unsw.dungeon;

import java.util.ArrayList;

public class Enemy extends MovableEntity implements GoalSubject {

   private Dungeon dungeon;
   private Player player;
   private ArrayList<GoalObserver> goalObservers;
   
   public Enemy(Dungeon dungeon, int x, int y) {
      super(dungeon, x, y, false);
      this.dungeon = dungeon;
      this.goalObservers = new ArrayList<GoalObserver>();
   }
   
   public void setPlayer(Player player) {
      this.player = player;
   }
   
   public void moveTowardsPlayer() {
      
      Direction d = null;
      
      int xDist = Math.abs(this.getX()-this.player.getX());
      int yDist = Math.abs(this.getY()-this.player.getY());
      
      if (xDist > yDist) {
         if (this.player.getX() < this.getX()) d = Direction.LEFT;
         else d = Direction.RIGHT;
      } else {
         if (this.player.getY() < this.getY()) d = Direction.UP;
         else d = Direction.DOWN;
      }
      
      // If player is invincible move in the opposite direction
      if (this.player.isInvincible()) {
         switch (d) {
         case UP:
            d = Direction.DOWN;
            break;
         case DOWN:
            d = Direction.UP;
            break;
         case LEFT:
            d = Direction.RIGHT;
            break;
         case RIGHT:
            d = Direction.LEFT;
            break;
         }
      }
      
      makeMove(d);
   }
   
	public Dungeon getDungeon() {
		return dungeon;
	}
	
	public void die() {
		notifyObserversOfIncrease();
	}

   @Override
   public void addObserver(GoalObserver o) {
      this.goalObservers.add(o);
   }

   @Override
   public void removeObserver(GoalObserver o) {
      this.goalObservers.remove(o);
   }

   @Override
   public void notifyObserversOfIncrease() {
      for (GoalObserver go : this.goalObservers) {
         go.increaseProgress();
      }  
   }
   
   @Override
   public void notifyObserversOfDecrease() {
      for (GoalObserver go : this.goalObservers) {
         go.decreaseProgress();
      }  
   }
}
