package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Player extends MovableEntity {

   private ArrayList<Integer> keyIDs; 
   private int swordHits;
   private int invincibilityLeft;

   /**
    * create a new player
    * @param dungeon the dungeon the player is contained within
    * @param x x position of the player
    * @param y y position of the player
    */
   public Player(Dungeon dungeon, int x, int y) {
      super(dungeon, x, y, false);
      this.keyIDs = new ArrayList<>();
      this.swordHits = 0;
      this.invincibilityLeft = 0;
   }

   /**
    * get the number of sword hits the player has before the sword is removed
    * @return
    */
   public int getSwordHits() {
      return this.swordHits;
   }
   
   /**
    * get the number of tiles left the player can traverse before their invincibility expires
    * @return
    */
   public int getInvincibilityLeft() {
      return this.invincibilityLeft;
   }

   /**
    * give the player a sword, with 5 hits remaining
    */
   public void pickupSword() {
      this.swordHits += 5;
   }
   
   /**
    * hit an enemy with a sword, taking one hit point away from the sword
    */
   public void hitEnemy() {
      if (this.swordHits > 0) {
         this.swordHits--;
      }
   }

   /**
    * turn the player invincible for a certain length of time (tiles travelled)
    */
   public void becomeInvincible() {
      this.invincibilityLeft += 15;
   }

   /**
    * check if the player is invincible
    * @return whether or not the player is invincible
    */
   public boolean isInvincible() {
      return this.invincibilityLeft > 0;
   }
   
   /**
    * check if the player has a sword
    * @return whether or not the player has a sword
    */
   public boolean hasSword() {
      return this.swordHits > 0;
   }
   
   /**
    * add a new key id to the player's inventory
    * @param ID the id of the key to add
    */
   public void addKey(int ID) {
      keyIDs.add(ID);
   }

   /**
    * move the player, and also decrease the anount of invincibility they have left
    */
   @Override
   public void makeMove(Direction d) {
      // Decrease invincibility when you move
      if (this.invincibilityLeft > 0) this.invincibilityLeft--;
         
      super.makeMove(d);
      
   }
}
