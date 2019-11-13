package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * a composite goal made up of multiple goals within the GoalComponent composite pattern
 * players must achieve all goals within to complete the goal
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class GoalAnd implements GoalComponent {

   private ArrayList<GoalComponent> subgoals;
   private BooleanProperty goalAchieved;
   private StringProperty goalProgress;

   /**
    * create a new composite-and goal
    * @param goals the goals within the composite
    */
   public GoalAnd(ArrayList<GoalComponent> goals) {
      this.subgoals = goals;
      this.goalAchieved = new SimpleBooleanProperty(false);
      this.goalProgress = new SimpleStringProperty(this.getProgress());
   }

   @Override
   public boolean isComplete() {
      // Update goalProgress
      this.goalProgress.set(this.getProgress());
      
      for (GoalComponent gc : this.subgoals) {
         if (!gc.isComplete()) return false;
      }
      this.goalAchieved.set(true);
      return true;
   }

   @Override
   public String getProgress() {
      int count = 0;
      for (GoalComponent gc : this.subgoals) {
         if (gc.isComplete()) count++;
      }
      return "AND Goal: " + count + " / " + this.subgoals.size();
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
