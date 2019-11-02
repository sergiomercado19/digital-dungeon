package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Treasure implements Item, GoalSubject {
	
   private ArrayList<GoalObserver> goalObservers;
	
	public Treasure() {
	   this.goalObservers = new ArrayList<GoalObserver>();
	}

	@Override
	public boolean canCollect() {
		// TODO Auto-generated method stub
		return false;
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
