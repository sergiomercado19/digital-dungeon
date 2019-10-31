package unsw.dungeon;

public interface KeySubject {
	public void addObserver(KeyObserver o);
	public void removeObserver(KeyObserver o);
	public void notifyObservers();
}
