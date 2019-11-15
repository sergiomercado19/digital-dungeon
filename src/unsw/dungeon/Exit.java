package unsw.dungeon;

import java.util.ArrayList;

/**
 * an exit to a dungeon
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Exit extends Entity implements GoalSubject {

	private ArrayList<GoalObserver> goalObservers;

	/**
	 * create a new exit
	 * @param x x position of the exit
	 * @param y y position of the exit
	 */
	public Exit(int x, int y) {
		super(x, y, false);
		this.goalObservers = new ArrayList<GoalObserver>();
	}

	/**
	 * when the player reaches the exit
	 */
	public void trigger() {
		notifyObserversOfIncrease();
	}

	@Override
	public void addObserver(GoalObserver o) {
		this.goalObservers.add(o);
	}

	@Override
	public void removeObserver(GoalObserver o) {
		this.goalObservers.remove(o);
	}

	@Override
	public void notifyObserversOfIncrease() {
		for (GoalObserver go : this.goalObservers) {
			go.increaseProgress();
		}  
	}

	@Override
	public void notifyObserversOfDecrease() {
		for (GoalObserver go : this.goalObservers) {
			go.decreaseProgress();
		}  
	}

	@Override
	public void collide(Player p, Direction d) {
		trigger();
	}

}
