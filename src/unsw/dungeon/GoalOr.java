package unsw.dungeon;

import java.util.ArrayList;

public class GoalOr implements GoalComponent {

private ArrayList<GoalComponent> goals;
   
   public GoalOr(ArrayList<GoalComponent> goals) {
      this.goals = goals;
   }
   
   @Override
   public boolean isComplete() {
      for (GoalComponent gc : this.goals) {
         if (gc.isComplete()) return true;
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
   
}
