package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class MovableEntity implements Entity {
   
   // IntegerProperty is used so that changes to the entities position can be
   // externally observed.
   private IntegerProperty x, y;
   protected boolean isSolid;
   private Dungeon dungeon;
   
   /**
    * Create an entity positioned in square (x,y)
    * @param x
    * @param y
    */
   public MovableEntity(Dungeon dungeon, int x, int y, boolean isSolid) {
      this.dungeon = dungeon;
      this.x = new SimpleIntegerProperty(x);
      this.y = new SimpleIntegerProperty(y);
      this.isSolid = isSolid;
   }
   
	public Dungeon getDungeon() {
	   return dungeon;
	}
	
	public void setPosition(int x, int y) {
		x().set(x);
		y().set(y);
	}
	
	public void makeMove(Direction d) {
	  int x = this.getX();
      int y = this.getY();
      
      switch (d) {
      case UP:
         if(dungeon.canMove(x, y - 1, this)) {
            setPosition(x, y - 1);
            // FIXME
            dungeon.registerMove(x, y - 1, d, this);
         }
         break;
      case DOWN:
         if(dungeon.canMove(x, y + 1, this)) {
        	 setPosition(x, y + 1);
        	 dungeon.registerMove(x, y + 1, d, this);
         }
         break;
      case LEFT:
         if(dungeon.canMove(x - 1, y, this)) {
        	 setPosition(x - 1, y);
        	 dungeon.registerMove(x - 1, y, d, this);
         }
         break;
      case RIGHT:
         if(dungeon.canMove(x + 1, y, this)) {
        	 setPosition(x + 1, y);
        	 dungeon.registerMove(x + 1, y, d, this);
         }
         break;
      }
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
