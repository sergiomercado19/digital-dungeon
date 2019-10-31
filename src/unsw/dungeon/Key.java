package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Key implements Item, KeySubject {
	
	public int ID;
	public List<KeyObserver> observers;
	
	public Key(int ID) {
		this.ID = ID;
		ArrayList<KeyObserver> observers = new ArrayList<>();
	}

	@Override
	public boolean canCollect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void collect(Player player) {
		// TODO Auto-generated method stub
		player.addKey(ID);
		// delete item
	}

	@Override
	public void addObserver(KeyObserver o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(KeyObserver o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}

	@Override
	public void notifyObservers() {
		for (KeyObserver o : observers) {
			o.update();
		}
	}

}
