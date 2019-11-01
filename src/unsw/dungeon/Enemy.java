package unsw.dungeon;

import java.lang.*;

public class Enemy extends Entity {

   private Player player;
   
   public Enemy(int x, int y) {
      super(x, y, false);
   }
   
   public void setPlayer(Player player) {
      this.player = player;
   }
   
   public void moveTowardsPlayer() {
      int xDist = Math.abs(this.getX()-this.player.getX());
      int yDist = Math.abs(this.getY()-this.player.getY());
      
      if (xDist > yDist) {
         if (this.player.getX() < this.getX()) makeMove(Direction.LEFT);
         else makeMove(Direction.RIGHT);
      } else {
         if (this.player.getY() < this.getY()) makeMove(Direction.UP);
         else makeMove(Direction.DOWN);
      }
   }
   
   public void makeMove(Direction dir) {
      
   }
   
}
