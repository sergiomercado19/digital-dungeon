package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * a goal that a player must achieve within the dungeon
 * leaf node of a GoalComponent in the composite pattern
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Goal implements GoalComponent, GoalObserver {

	private String name;
	private int currentValue;
	private int targetValue;
	private BooleanProperty goalAchieved;
	private StringProperty goalProgress;

	/**
	 * create a new goal
	 * @param name the name of the goal
	 * @param targetValue the value to reach to complete the goal
	 */
	public Goal(String name, int targetValue) {
		this.name = name;
		this.currentValue = 0;
		this.targetValue = targetValue;
		this.goalAchieved = new SimpleBooleanProperty(false);
		this.goalProgress = new SimpleStringProperty(this.getProgress());
	}

	@Override
	public boolean isComplete() {
		return this.currentValue == this.targetValue;
	}

	@Override
	public String getProgress() {
		return this.name + ": " + this.currentValue + " / " + this.targetValue;
	}

	@Override
	public void increaseProgress() {
		this.currentValue++;
		if (this.currentValue == this.targetValue) this.goalAchieved.set(true);

		// Update goalProgress
		this.goalProgress.set(this.getProgress());
	}

	@Override
	public void decreaseProgress() {
		this.currentValue--;
		if (this.currentValue == this.targetValue) this.goalAchieved.set(true);

		// Update goalProgress
		this.goalProgress.set(this.getProgress());
	}

	@Override
	public BooleanProperty goalAchieved() {
		return this.goalAchieved;
	}

	@Override
	public ArrayList<GoalComponent> getSubgoals() {
		// Since there are no subgoals
		return null;
	}

	@Override
	public StringProperty goalProgress() {
		return this.goalProgress;
	}

}
