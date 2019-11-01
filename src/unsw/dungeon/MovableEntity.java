package unsw.dungeon;

import javafx.beans.property.IntegerProperty;

public interface MovableEntity {
	Dungeon getDungeon();
	public void makeMove(Direction d);
	public int getX();
	public int getY();
	public IntegerProperty y();
	public IntegerProperty x();
}
