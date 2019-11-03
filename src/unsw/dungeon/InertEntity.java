package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An inert (non-moving) entity in the dungeon.
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class InertEntity implements Entity {

   // IntegerProperty is used so that changes to the entities position can be
   // externally observed.
   private IntegerProperty x, y;
   protected boolean isSolid;

   /**
    * create a new entity
    * @param x x position of the entity
    * @param y y position of the entity
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
