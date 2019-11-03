package unsw.dungeon;

/**
 * a portal, which can be used to travel to its corresponding portal elsewhere in the dungeon
 * @author Sergio Mercado Ruiz & Rory Madden
 *
 */
public class Portal extends InertEntity {
	
	private Portal link;
	private int ID;

	/**
	 * create a new portal
	 * @param x x position of the portal
	 * @param y y position of the portal
	 * @param ID the id of the portal, to link to another portal of the same id
	 */
	public Portal(int x, int y, int ID) {
		super(x, y, false);
		this.link = null;
		this.ID = ID;
	}
	
	/**
	 * link to a portal with the same id, to teleport to when touched
	 * @param portal the portal to link to
	 */
	public void linkTo(Portal portal) {
		link = portal;
	}
	
	/**
	 * teleport a player who touches the portal to the corresponding linked portal
	 * @param player
	 */
	public void teleport(Player player) {
	   if (link != null) {	      
	      player.setPosition(link.getX(), link.getY());
	   }
	}

	/**
	 * get the id of the portal
	 * @return the id of the portal
	 */
	public Object getID() {
		return ID;
	}
}
