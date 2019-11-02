package unsw.dungeon;

import java.util.ArrayList;

public class Treasure implements Item, GoalSubject {
	
   private ArrayList<GoalObserver> goalObservers;
	
	public Treasure() {
	   this.goalObservers = new ArrayList<GoalObserver>();
	}

	@Override
	public boolean canCollect() {
		return true;
	}

	@Override
	public void collect(Player player) {
		notifyObserversOfIncrease();
		// delete item
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
