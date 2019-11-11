package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Player extends Entity implements Movable {

   private Dungeon dungeon;
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
      super(x, y, false);
      this.dungeon = dungeon;
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
   public void pickupSword(Sword s) {
      // only pick up if player doesn't already have a sword
      if (this.swordHits == 0) {
         this.swordHits += 5;
         this.dungeon.removeEntity(s);
      }
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
   public void becomeInvincible(Invincibility i) {
      this.invincibilityLeft += 15;
      this.dungeon.removeEntity(i);
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
   public void pickupKey(Key k) {
      keyIDs.add(k.getID());
      this.dungeon.removeEntity(k);
   }

   public void pickupTreasure(Treasure t) {
      this.dungeon.removeEntity(t);
   }

   /**
    * move the player, and also decrease the amount of invincibility they have left
    */
   public void makeMove(Direction d) {
      int x, y;
      x = getX();
      y = getY();

      switch (d) {
      case UP:
         y = y - 1;
         break;
      case DOWN:
         y = y + 1;
         break;
      case LEFT:
         x = x - 1;
         break;
      case RIGHT:
         x = x + 1;
         break;
      }

      ArrayList<Entity> tileEntities = dungeon.checkTile(x, y);
      boolean canMove = true;
      for(Entity e : tileEntities) {
         if(!e.canCollide(this, d)) canMove = false;
      }
      if(canMove) {
         setPosition(x, y);

         // Decrease invincibility when you move
         if (this.invincibilityLeft > 0) this.invincibilityLeft--;

         for(Entity e : tileEntities) {
            e.collide(this, d);
         }

         // tell the dungeon we moved
         dungeon.registerPlayerMove();
      }
   }


   @Override
   public void collide(Enemy e, Direction d) {
      // they gotta fight
      System.out.println("player registered");
      dungeon.fight(this, e);
   }

   @Override
   public void setPosition(int x, int y) {
      x().set(x);
      y().set(y);
   }
}
