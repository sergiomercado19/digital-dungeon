package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Treasure implements Item, GoalSubject {
	
	private List<GoalObserver> observers;
	
	public Treasure() {
		observers = new ArrayList<GoalObserver>();
	}

	@Override
	public boolean canCollect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void collect(Player player) {
		notifyObserversOfIncrease();
		// delete item
	}

	@Override
	public void addObserver(GoalObserver o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(GoalObserver o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
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
