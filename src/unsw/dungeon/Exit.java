package unsw.dungeon;

import java.util.ArrayList;

public class Exit extends InertEntity implements GoalSubject {

   private ArrayList<GoalObserver> goalObservers;
   
	public Exit(int x, int y) {
		super(x, y, false);
		this.goalObservers = new ArrayList<GoalObserver>();
	}
	
	public void trigger() {
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
