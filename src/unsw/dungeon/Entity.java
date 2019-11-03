package unsw.dungeon;

import javafx.beans.property.IntegerProperty;

/**
 * interface for an entity in the dungeon.
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public interface Entity {
	/**
	 * 
	 * @return
	 */
    public IntegerProperty x();
    public IntegerProperty y();
    public int getY();
    public int getX();
    public boolean isSolid();
    public void setSolid(boolean isSolid);
}
