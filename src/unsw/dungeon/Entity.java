package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public interface Entity {

    public IntegerProperty x();

    public IntegerProperty y();

    public int getY();

    public int getX();
    
    public boolean getSolid();
    
    public void setSolid(boolean isSolid);
    
}
