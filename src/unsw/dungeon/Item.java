package unsw.dungeon;

public interface Item {
	public boolean canCollect();
	public void collect(Player player);
}
