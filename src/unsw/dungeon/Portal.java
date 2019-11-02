package unsw.dungeon;

public class Portal extends InertEntity {
	
	public Portal link;

	public Portal(int x, int y) {
		super(x, y, false);
		this.link = null;
	}
	
	public void linkTo(Portal portal) {
		link = portal;
	}
	
	public void teleport(Player player) {
	   if (link != null) {	      
	      player.setPosition(link.getX(), link.getY());
	   }
	}
}
