package unsw.dungeon;

import java.util.ArrayList;

/**
 * interface for a goal tree by the composite pattern
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public interface GoalComponent {
   /**
    * check whether the goal has been completed yet
    * @return whether or not the goal has been completed
    */
   public boolean isComplete();
   /**
    * get the current progress of the goal as an array of strings
    * strings are formatted like "goalName: currentValue/targetValue"
    * @return an array of strings of goal progress
    */
   public ArrayList<String> getProgress();
}
