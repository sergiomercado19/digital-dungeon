package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;

/**
 * a composite goal made up of multiple goals within the GoalComponent composite pattern
 * players must achieve at least one goal within to complete the goal
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class GoalOr implements GoalComponent {

   private ArrayList<GoalComponent> goals;
   private BooleanProperty goalAchieved;
   /**
    * create a new composite-or goal
    * @param goals the goals within the composite
    */
   public GoalOr(ArrayList<GoalComponent> goals) {
      this.goals = goals;
   }

   @Override
   public boolean isComplete() {
      for (GoalComponent gc : this.goals) {
         if (gc.isComplete()) {
            this.goalAchieved.set(true);
            return true;
         }
      }
      return false;
   }

   @Override
   public ArrayList<String> getProgress() {
      ArrayList<String> res = new ArrayList<String>();
      String str = "One of:";
      for (GoalComponent gc : this.goals) {
         str = str.concat("\n\t" + gc.getProgress() + ",");
      }
      return res;
   }

   @Override
   public BooleanProperty goalAchieved() {
      return this.goalAchieved;
   }

}
