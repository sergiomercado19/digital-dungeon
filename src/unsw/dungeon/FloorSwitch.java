package unsw.dungeon;

import java.util.ArrayList;

/**
 * a floor switch in the dungeon, which can be triggered by a boulder
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class FloorSwitch extends InertEntity implements GoalSubject {
   
   private ArrayList<GoalObserver> goalObservers;

   /**
    * create a new floor switch
    * @param x x position of the floor switch
    * @param y y position of the floor switch
    */
	public FloorSwitch(int x, int y) {
		super(x, y, false);
		this.goalObservers = new ArrayList<GoalObserver>();
	}
	
	/**
	 * activate the floor switch (when a boulder is on top)
	 */
	public void activate() {
		notifyObserversOfIncrease();
	}
	
	/**
	 * deactivate the floor switch (when a boulder is no longer on top)
	 */
	public void deactivate() {
		notifyObserversOfDecrease();
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
