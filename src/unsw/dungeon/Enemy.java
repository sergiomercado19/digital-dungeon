package unsw.dungeon;

public class Enemy extends MovableEntity {

   private Dungeon dungeon;
   private Player player;
   
   public Enemy(Dungeon dungeon, int x, int y) {
      super(dungeon, x, y, false);
      this.dungeon = dungeon;
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
   
	public Dungeon getDungeon() {
		return dungeon;
	}
   
}
