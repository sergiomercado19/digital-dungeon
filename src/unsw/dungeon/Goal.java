package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;

/**
 * a goal that a player must achieve within the dungeon
 * leaf node of a GoalComponent in the composite pattern
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Goal implements GoalComponent, GoalObserver {

   private String name;
   private int currentValue;
   private int targetValue;
   private BooleanProperty goalAchieved;
   
   /**
    * create a new goal
    * @param name the name of the goal
    * @param targetValue the value to reach to complete the goal
    */
   public Goal(String name, int targetValue) {
      this.name = name;
      this.currentValue = 0;
      this.targetValue = targetValue;
   }

   @Override
   public boolean isComplete() {
      return this.currentValue == this.targetValue;
   }

   @Override
   public ArrayList<String> getProgress() {
      ArrayList<String> res = new ArrayList<String>();
      res.add(this.name + ": " + this.currentValue + "/" + this.targetValue);
      return res;
   }

   @Override
   public void increaseProgress() {
      this.currentValue++;
      if (this.currentValue == this.targetValue) this.goalAchieved.set(true);
   }

   @Override
   public void decreaseProgress() {
      this.currentValue--;
      if (this.currentValue == this.targetValue) this.goalAchieved.set(true);
   }

   @Override
   public BooleanProperty goalAchieved() {
      return this.goalAchieved;
   }
}
