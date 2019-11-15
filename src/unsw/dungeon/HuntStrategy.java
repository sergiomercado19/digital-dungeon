package unsw.dungeon;

public class HuntStrategy implements EnemyStrategy {

   @Override
   public void moveEnemy(Dungeon dungeon, Player player, Enemy enemy) {
      
      if (player != null) {         
         Direction d = null;

         int xDist = Math.abs(enemy.getX()-player.getX());
         int yDist = Math.abs(enemy.getY()-player.getY());

         if (xDist > yDist) {
            
            // Try moving in the X axis
            if (player.getX() < enemy.getX()) d = Direction.LEFT;
            else d = Direction.RIGHT;

            if (enemy.makeMove(getDirection(player, enemy, d))) return;
            
            // Try moving in the Y axis
            if (player.getY() < enemy.getY()) d = Direction.UP;
            else d = Direction.DOWN;
            
            enemy.makeMove(getDirection(player, enemy, d));
            
         } else {
            
            // Try moving in the Y axis
            if (player.getY() < enemy.getY()) d = Direction.UP;
            else d = Direction.DOWN;

            if (enemy.makeMove(getDirection(player, enemy, d))) return;
            
            // Try moving in the X axis
            if (player.getX() < enemy.getX()) d = Direction.LEFT;
            else d = Direction.RIGHT;
            
            enemy.makeMove(getDirection(player, enemy, d));
            
         }

      }
   }
   
   // If player is invincible move in the opposite direction
   private Direction getDirection(Player p, Enemy e, Direction d) {
      if (p.isInvincible().get()) {
         return e.changeDirection(d);
      }
      
      return d;
   }
}
