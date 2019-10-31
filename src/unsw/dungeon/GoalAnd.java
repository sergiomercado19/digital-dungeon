package unsw.dungeon;

import java.util.ArrayList;

public class GoalAnd implements GoalComponent {

   private ArrayList<GoalComponent> goals;
   
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
