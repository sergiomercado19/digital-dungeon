package unsw.dungeon;

public interface GoalSubject {
	public void addObserver(GoalObserver o);
	public void removeObserver(GoalObserver o);
	public void notifyObserversOfIncrease();
	public void notifyObserversOfDecrease();
}
