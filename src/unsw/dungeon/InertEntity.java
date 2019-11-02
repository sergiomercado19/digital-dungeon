package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class InertEntity implements Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    protected boolean isSolid;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public InertEntity(int x, int y, boolean isSolid) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.isSolid = isSolid;
    }

    @Override
    public IntegerProperty x() {
        return x;
    }

    @Override
    public IntegerProperty y() {
        return y;
    }

    @Override
    public int getY() {
        return y().get();
    }

    @Override
    public int getX() {
        return x().get();
    }
    
    @Override
    public boolean isSolid() {
    	return isSolid;
    }
    
    @Override
    public void setSolid(boolean isSolid) {
    	this.isSolid = isSolid;
    }
}
