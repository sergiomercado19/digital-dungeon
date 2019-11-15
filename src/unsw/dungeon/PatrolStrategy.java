package unsw.dungeon;

public class PatrolStrategy implements EnemyStrategy {

   private Direction d;
   
   public PatrolStrategy(Direction d) {
      super();
      this.d = d;
   }

   @Override
   public void moveEnemy(Dungeon dungeon, Player player, Enemy enemy) {

      if (enemy.makeMove(this.d)) {
         return;
      } else {
         // Change direction
         switch (this.d) {
         case UP:
            this.d = Direction.DOWN;
            break;
         case DOWN:
            this.d = Direction.UP;
            break;
         case LEFT:
            this.d =  Direction.RIGHT;
            break;
         case RIGHT:
            this.d = Direction.LEFT;
            break;
         }
      }
      
   }
}
