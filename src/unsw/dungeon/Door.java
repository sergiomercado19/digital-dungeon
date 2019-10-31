package unsw.dungeon;

public class Door extends Entity implements KeyObserver {
	
	private boolean isOpen;
	private int keyID;

	public Door(int x, int y, int keyID) {
		super(x, y);
		isOpen = false;
		this.keyID = keyID;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		isOpen = true;
	}

}
