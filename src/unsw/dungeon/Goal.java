package unsw.dungeon;

import java.util.ArrayList;

public class Goal implements GoalComponent, GoalObserver {
   
   private String name;
   private int currentValue;
   private int targetValue;
   
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
	}
   
	public void decreaseProgress() {
      this.currentValue--;
   }
}
