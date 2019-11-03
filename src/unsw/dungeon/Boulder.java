package unsw.dungeon;

/**
 * a boulder, which can be pushed by a player to trigger a floor switch
 * @author Rory
 *
 */
public class Boulder extends MovableEntity {
	
   private Dungeon dungeon;
   private FloorSwitch floorSwitch;

   /**
    * create a new boulder
    * @param dungeon the dungeon of the boulder
    * @param x x position of boulder
    * @param y y position of boulder
    */
	public Boulder(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y, false);
		this.dungeon = dungeon;
		this.floorSwitch = null;
	}
	
	/**
	 * push the boulder in a direction
	 * @param d the direction to push
	 */
	public void push(Direction d) {
		// FIXME
		// check
		// then
		makeMove(d);
	}

	/**
	 * get the dungeon the boulder is contained in
	 */
	public Dungeon getDungeon() {
		return dungeon;
	}

	/**
	 * trigger a floor switch when the boulder is on top
	 * @param s
	 */
   public void activateSwitch(FloorSwitch s) {
      this.floorSwitch = s;
      this.floorSwitch.activate();
   }

   /**
    * untrigger a floor switch when a boulder is moved off it
    */
   public void deactivateSwitch() {
      if (this.floorSwitch != null) {         
         this.floorSwitch.deactivate();
         this.floorSwitch = null;
      }
   }

   /**
    * when the boulder is moved, the floor switch is also untriggered
    */
   @Override
   public void makeMove(Direction d) {
      // Deactivate floorSwitch before moving
      this.deactivateSwitch();
      
      super.makeMove(d);
   }

}
