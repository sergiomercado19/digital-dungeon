package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * a door, which can be opened with a key
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Door extends Entity {

   private int ID;
   private BooleanProperty isUnlocked;

   /**
    * create a new door
    * @param x x position of the door
    * @param y y position of the door
    * @param ID the id of the key that can open the door
    */
   public Door(int x, int y, int ID) {
      super(x, y, true);
      this.ID = ID;
      this.isUnlocked = new SimpleBooleanProperty(false);
   }
   
   public BooleanProperty isUnlocked() {
	   return this.isUnlocked;
   }

   /**
    * get the id of the door
    * @return the id of the door
    */
   public int getID() {
      return ID;
   }

   /**
    * set the door to an open state so players can pass through
    */
   public void unlock() {
      super.setSolid(false);
      isUnlocked.set(true);
   }
}
