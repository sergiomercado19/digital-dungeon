package unsw.dungeon;

public class Portal extends InertEntity {
	
	private Portal link;
	private int ID;

	public Portal(int x, int y, int ID) {
		super(x, y, false);
		this.link = null;
		this.ID = ID;
	}
	
	public void linkTo(Portal portal) {
		link = portal;
	}
	
	public void teleport(Player player) {
	   if (link != null) {	      
	      player.setPosition(link.getX(), link.getY());
	   }
	}

	public Object getID() {
		return ID;
	}
}
