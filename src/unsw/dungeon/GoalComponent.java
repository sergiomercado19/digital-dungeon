package unsw.dungeon;

import java.util.ArrayList;

/**
 * interface for a goal tree by the composite pattern
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public interface GoalComponent {
	public boolean isComplete();
	public ArrayList<String> getProgress();
}
