package unsw.dungeon;

public class Collectable extends Entity {
	
	private Item item;

	public Collectable(int x, int y, Item item) {
		super(x, y);
		this.item = item;
		isSolid = false;
		// TODO Auto-generated constructor stub
	}
	
	public boolean canCollect() {
		return item.canCollect();
	}
	
	public void collect(Player player) {
		item.collect(player);
	}

}
