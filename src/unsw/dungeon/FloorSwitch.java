package unsw.dungeon;

public class FloorSwitch extends InertEntity implements GoalSubject {

	public FloorSwitch(int x, int y) {
		super(x, y, false);
	}
	
	public void trigger() {
		notifyObserversOfIncrease();
	}
	
	public void unTrigger() {
		notifyObserversOfDecrease();
	}

	@Override
	public void addObserver(GoalObserver o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver(GoalObserver o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserversOfIncrease() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObserversOfDecrease() {
		// TODO Auto-generated method stub
		
	}
}
