package unsw.dungeon;

import java.util.ArrayList;

/**
 * interface for a goal tree by the composite pattern
 * @author Rory
 *
 */
public interface GoalComponent {
	public boolean isComplete();
	public ArrayList<String> getProgress();
}
