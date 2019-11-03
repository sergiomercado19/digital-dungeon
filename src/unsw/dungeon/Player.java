package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends MovableEntity {

   private ArrayList<Integer> keyIDs; 
   private int swordHits;
   private int invincibilityLeft;

   /**
    * Create a player positioned in square (x,y)
    * @param x
    * @param y
    */
   public Player(Dungeon dungeon, int x, int y) {
      super(dungeon, x, y, false);
      this.keyIDs = new ArrayList<>();
      this.swordHits = 0;
      this.invincibilityLeft = 0;
   }

   public void getSword() {
      this.swordHits += 5;
   }

   public void becomeInvincible() {
      this.invincibilityLeft += 15;
   }

   public boolean isInvincible() {
      return this.invincibilityLeft > 0;
   }
   
   public boolean hasSword() {
      return this.swordHits > 0;
   }
   
   public void addKey(int ID) {
      keyIDs.add(ID);
   }

   @Override
   public void makeMove(Direction d) {
      // Decrease invincibility when you move
      if (this.invincibilityLeft > 0) this.invincibilityLeft--;
         
      super.makeMove(d);
   }
}
