package unsw.dungeon;

public class Portal extends InertEntity {
	
	public Portal link;

	public Portal(int x, int y) {
		super(x, y, false);
		// TODO Auto-generated constructor stub
	}
	
	public void linkTo(Portal portal) {
		link = portal;
	}
	
	public void teleport(Player player) {
		player.setPosition(link.getX(), link.getY());
	}
}
