package unsw.dungeon;

public class Key implements Item {
	
	public int ID;
	
	public Key(int ID) {
		this.ID = ID;
	}

	@Override
	public boolean canCollect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void collect(Player player) {
		// TODO Auto-generated method stub
		player.addKey(ID);
		// delete item
	}

}
