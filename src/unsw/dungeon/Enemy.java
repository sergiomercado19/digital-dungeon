package unsw.dungeon;

public class Enemy extends Entity implements MovableEntity {

   private Player player;
   private Dungeon dungeon;
   
   public Enemy(Dungeon dungeon, int x, int y, Player player) {
      super(x, y, false);
      this.dungeon = dungeon;
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
   
   public void makeMove(Direction d) {
	   MoveContext move = null;
	   	switch(d) {
	   	case UP:
	   		move = new MoveContext(new MoveUp());
				break;
	   	case DOWN:
	   		move = new MoveContext(new MoveDown());
	   		break;
	   	case LEFT:
	   		move = new MoveContext(new MoveLeft());
	   		break;
	   	case RIGHT:
	   		move = new MoveContext(new MoveRight());
	   		break;
	   	}
	   	move.doMove(this);
   }

	public Dungeon getDungeon() {
		return dungeon;
	}
   
}
