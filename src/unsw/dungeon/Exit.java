package unsw.dungeon;

public class Exit extends InertEntity implements GoalSubject {

	public Exit(int x, int y) {
		super(x, y, false);
		// TODO Auto-generated constructor stub
	}
	
	public void trigger() {
		notifyObserversOfIncrease();
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
