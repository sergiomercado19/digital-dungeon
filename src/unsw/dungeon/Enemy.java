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
      int xDist = Math.abs(this.getX()-this.player.getX());
      int yDist = Math.abs(this.getY()-this.player.getY());
      
      if (xDist > yDist) {
         if (this.player.getX() < this.getX()) makeMove(Direction.LEFT);
         else makeMove(Direction.RIGHT);
      } else {
         if (this.player.getY() < this.getY()) makeMove(Direction.UP);
         else makeMove(Direction.DOWN);
      }
   }
   
	public Dungeon getDungeon() {
		return dungeon;
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
