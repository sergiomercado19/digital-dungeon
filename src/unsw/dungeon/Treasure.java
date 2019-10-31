package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Treasure implements Item, GoalSubject {
	
	// this needs to be initialised but... there's no constructor
	private List<GoalObserver> observers;
	// observers = new ArrayList<GoalObserver>();

	@Override
	public boolean canCollect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void collect(Player player) {
		// TODO Auto-generated method stub
		
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
	public void notifyObservers() {
		for (GoalObserver o : observers) {
			o.update();
		}
	}

}
