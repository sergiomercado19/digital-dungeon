package unsw.dungeon;

import java.util.ArrayList;

/**
 * a "pile of treasure", which when picked up will go towards the total treasure the player has picked up
 * Collectable under the strategy pattern
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Treasure extends Entity implements Item, GoalSubject {

   private ArrayList<GoalObserver> goalObservers;

   public Treasure(int x, int y) {
      super(x, y, false);
      this.goalObservers = new ArrayList<GoalObserver>();
   }

   @Override
   public void collect(Player player) {
      notifyObserversOfIncrease();
      player.pickupTreasure(this);
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

   @Override
   public void collide(Player p, Direction d) {
      collect(p);
   }

}
