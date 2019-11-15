package unsw.dungeon;

public class IneptStrategy implements EnemyStrategy {

   @Override
   public void moveEnemy(Dungeon dungeon, Player player, Enemy enemy) {
      
      if (player != null) {         
         Direction d = null;

         int xDist = Math.abs(enemy.getX()-player.getX());
         int yDist = Math.abs(enemy.getY()-player.getY());

         if (xDist > yDist) {
            if (player.getX() < enemy.getX()) d = Direction.LEFT;
            else d = Direction.RIGHT;
         } else {
            if (player.getY() < enemy.getY()) d = Direction.UP;
            else d = Direction.DOWN;
         }

         // If player is invincible move in the opposite direction
         if (player.isInvincible().get()) {
            switch (d) {
            case UP:
               d = Direction.DOWN;
               break;
            case DOWN:
               d = Direction.UP;
               break;
            case LEFT:
               d = Direction.RIGHT;
               break;
            case RIGHT:
               d = Direction.LEFT;
               break;
            }
         }

         enemy.makeMove(d);
      }

   }

}
