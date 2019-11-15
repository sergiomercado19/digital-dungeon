package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

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
	 * get the current progress of the goal as a string
	 * strings are formatted like "goalName: currentValue/targetValue"
	 * @return an array of strings of goal progress
	 */
	public String getProgress();
	/**
	 * observable property of whether the goal has been achieved
	 * @return
	 */
	public BooleanProperty goalAchieved();
	/**
	 * observeable property of the current string form of the goal's progress
	 * @return
	 */
	public StringProperty goalProgress();
	/**
	 * array of all subgoals of the goal
	 * @return
	 */
	public ArrayList<GoalComponent> getSubgoals();
}
