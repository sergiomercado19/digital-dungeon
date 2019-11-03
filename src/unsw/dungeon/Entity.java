package unsw.dungeon;

import javafx.beans.property.IntegerProperty;

/**
 * interface for an entity in the dungeon.
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public interface Entity {
   /**
    * integer property for x
    */
   public IntegerProperty x();
   /**
    * integer property for y
    */
   public IntegerProperty y();
   /**
    * get the current y position of the entity
    * @return the current y position
    */
   public int getY();
   /**
    * get the current x position of the entity
    * @return the current x position
    */
   public int getX();
   /**
    * check if the entity is solid (if it allows other entities to pass through)
    * @return whether or not the entity is solid
    */
   public boolean isSolid();
   /**
    * set the solidity state of an entity
    * @param isSolid the solidity state to set
    */
   public void setSolid(boolean isSolid);
}
