package unsw.dungeon;

/**
 * interface for a goal observer
 * observes a subject, updating progress on a goal accordingly
 * @author Rory
 *
 */
public interface GoalObserver {
	public void increaseProgress();
	public void decreaseProgress();
}
