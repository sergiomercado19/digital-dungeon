package unsw.dungeon;

public class Boulder extends MovableEntity {
	
   private Dungeon dungeon;
   private FloorSwitch floorSwitch;

	public Boulder(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y, false);
		this.dungeon = dungeon;
		this.floorSwitch = null;
	}
	
	public void push(Direction d) {
		// FIXME
		// check
		// then
		makeMove(d);
	}

	public Dungeon getDungeon() {
		return dungeon;
	}

   public void activateSwitch(FloorSwitch s) {
      this.floorSwitch = s;
      this.floorSwitch.activate();
   }

   public void deactivateSwitch() {
      if (this.floorSwitch != null) {         
         this.floorSwitch.deactivate();
         this.floorSwitch = null;
      }
   }

   @Override
   public void makeMove(Direction d) {
      // Deactivate floorSwitch before moving
      this.deactivateSwitch();
      
      super.makeMove(d);
   }

}
