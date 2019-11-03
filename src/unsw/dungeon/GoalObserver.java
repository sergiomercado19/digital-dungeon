package unsw.dungeon;

/**
 * interface for a goal observer
 * observes a subject, updating progress on a goal accordingly
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public interface GoalObserver {
	public void increaseProgress();
	public void decreaseProgress();
}
