package unsw.dungeon;

/**
 * interface for a goal subject
 * updates its observers based on the state of the subject in achieving progress towards some goal
 * @author Rory
 *
 */
public interface GoalSubject {
	public void addObserver(GoalObserver o);
	public void removeObserver(GoalObserver o);
	public void notifyObserversOfIncrease();
	public void notifyObserversOfDecrease();
}
