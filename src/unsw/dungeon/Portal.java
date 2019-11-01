package unsw.dungeon;

public class Portal extends InertEntity {
	
	public Portal link;

	public Portal(int x, int y) {
		super(x, y, false);
		// TODO Auto-generated constructor stub
	}
	
	public void linkTo(Portal p) {
		link = p;
	}
	
	public void teleport(Player p) {
		p.setPosition(link.getX(), link.getY());
	}
}
