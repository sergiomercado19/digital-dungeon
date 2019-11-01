package unsw.dungeon;

public class Collectable extends InertEntity {
	
	private Item item;

	public Collectable(int x, int y, Item item) {
		super(x, y, false);
		this.item = item;
		// TODO Auto-generated constructor stub
	}
	
	public boolean canCollect() {
		return item.canCollect();
	}
	
	public void collect(Player player) {
		item.collect(player);
	}

}
