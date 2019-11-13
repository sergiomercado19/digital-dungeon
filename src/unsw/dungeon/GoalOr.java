package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * a composite goal made up of multiple goals within the GoalComponent composite pattern
 * players must achieve at least one goal within to complete the goal
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class GoalOr implements GoalComponent {

   private ArrayList<GoalComponent> subgoals;
   private BooleanProperty goalAchieved;
   private StringProperty goalProgress;
   
   /**
    * create a new composite-or goal
    * @param goals the goals within the composite
    */
   public GoalOr(ArrayList<GoalComponent> goals) {
      this.subgoals = goals;
      this.goalAchieved = new SimpleBooleanProperty(false);
      this.goalProgress = new SimpleStringProperty(this.getProgress());
   }

   @Override
   public boolean isComplete() {
      // Update goalProgress
      this.goalProgress.set(this.getProgress());
      
      for (GoalComponent gc : this.subgoals) {
         if (gc.isComplete()) {
            this.goalAchieved.set(true);
            return true;
         }
      }
      return false;
   }

   @Override
   public String getProgress() {
      int count = 0;
      if (this.isComplete()) count = 1;
      return "OR Goal: " + count + " / 1";
   }

   @Override
   public BooleanProperty goalAchieved() {
      return this.goalAchieved;
   }

   @Override
   public ArrayList<GoalComponent> getSubgoals() {
      return this.subgoals;
   }

   @Override
   public StringProperty goalProgress() {
      return this.goalProgress;
   }
   
}
