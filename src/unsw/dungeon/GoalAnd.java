package unsw.dungeon;

import java.util.ArrayList;

/**
 * a composite goal made up of multiple goals within the GoalComponent composite pattern
 * players must achieve all goals within to complete the goal
 * @author Rory
 *
 */
public class GoalAnd implements GoalComponent {

   private ArrayList<GoalComponent> goals;
   
   /**
    * create a new composite-and goal
    * @param goals the goals within the composite
    */
   public GoalAnd(ArrayList<GoalComponent> goals) {
      this.goals = goals;
   }
   
   @Override
   public boolean isComplete() {
      for (GoalComponent gc : this.goals) {
         if (!gc.isComplete()) return false;
      }
      return true;
   }
   
   @Override
   public ArrayList<String> getProgress() {
      ArrayList<String> res = new ArrayList<String>();
      for (GoalComponent gc : this.goals) {
         res.addAll(gc.getProgress());
      }
      return res;
   }
   
}
